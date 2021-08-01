package io.spring.batch.configuration;

import io.spring.batch.listeners.DatabaseAlterSequenceListener;
import io.spring.batch.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.PrintWriter;


@Configuration
public class Config {


    @Bean
    public DatabaseAlterSequenceListener databaseAlterSequenceListener() {
        return new DatabaseAlterSequenceListener();
    }


    @Bean
    CustomerService customerService() {
        return new CustomerService();
    }


    @Bean
    JobConfiguration.InsertCustomerCompositeItemWriter updateItemWriter() {
        return new JobConfiguration.InsertCustomerCompositeItemWriter();
    }

}