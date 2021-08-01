/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.spring.batch.configuration;

import javax.sql.DataSource;

import io.spring.batch.Constant.ProcessConstant;
import io.spring.batch.domain.*;

import io.spring.batch.listeners.DatabaseAlterSequenceListener;
import io.spring.batch.mapper.CustomerFieldSetMapper;
import io.spring.batch.partition.RangePartitioner;
import io.spring.batch.proccessor.FilteringItemProcessor;
import io.spring.batch.readers.SynchronizedItemReaderDecorator;
import io.spring.batch.services.*;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michael Minella
 */
@Configuration
public class JobConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Autowired
    public DatabaseAlterSequenceListener databaseAlterSequenceListener;

    @Autowired
    public InsertCustomerCompositeItemWriter insertCustomerCompositeItemWriter;

    @Bean
    public RangePartitioner rangePartitioner() {
        return new RangePartitioner();
    }

    @Bean
    ItemReader<Customer> databaseXmlItemReader() {
        JdbcCursorItemReader<Customer> databaseReader = new JdbcCursorItemReader<>();
        databaseReader.setDataSource(dataSource);
        databaseReader.setSql("select * from migrate.migrate_customer where transfered_id is null");
        databaseReader.setRowMapper(new BeanPropertyRowMapper<>(Customer.class));
        return databaseReader;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor("spring_batch");
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }


    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Customer, ItemWriterDto>chunk(ProcessConstant.CHUNK_SIZE)
//                .reader(slaveReader(null,null,null))
                .reader(databaseXmlItemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .faultTolerant()
                .skip(EmptyResultDataAccessException.class)
                .skipLimit(ProcessConstant.SKIP_LIMIT)
                .listener(databaseAlterSequenceListener)
//                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Step masterStep() throws Exception {
        return stepBuilderFactory.get("masterStep").partitioner(step1().getName(), rangePartitioner())
                .partitionHandler(masterSlaveHandler()).build();
    }


    @Bean
    public FilteringItemProcessor itemProcessor() {
        return new FilteringItemProcessor();
    }

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("job")
                .start(step1())
                .incrementer(new RunIdIncrementer())
                .build();
    }


    @Bean
    public ItemWriter<ItemWriterDto> itemWriter() {
        CompositeItemWriter<ItemWriterDto> compositeItemWriter = new CompositeItemWriter<>();
        compositeItemWriter.setDelegates(Arrays.asList( insertCustomerCompositeItemWriter));
        return compositeItemWriter;
    }

    @Bean
    public PartitionHandler masterSlaveHandler() throws Exception {
        TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
        handler.setGridSize(30);
        handler.setTaskExecutor(taskExecutor());
        handler.setStep(step1());
        try {
            handler.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return handler;
    }

    public static class InsertCustomerCompositeItemWriter implements ItemWriter<ItemWriterDto> {

        @Autowired
        CustomerService customerService;

        @Override
        public void write(List<? extends ItemWriterDto> items) {
            for (ItemWriterDto itemWriterDto : items) {
                if (itemWriterDto.getCustomer() != null && itemWriterDto.isUpdateFlag() == false) {
                    customerService.insertCustomer(itemWriterDto.getCustomer());
                }
            }
        }
    }

}
