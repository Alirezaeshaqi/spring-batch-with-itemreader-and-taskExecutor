package io.spring.batch.listeners;


import io.spring.batch.proccessor.FilteringItemProcessor;
import io.spring.batch.services.Sequences;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.Data;


public class DatabaseAlterSequenceListener implements StepExecutionListener {

    @Autowired
    FilteringItemProcessor filteringItemProcessor ;

    @Autowired
    private Sequences sequences ;

    @Override
    public void beforeStep(StepExecution stepExecution) {

        /*implement some business before step starts*/
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        /*implement some business before step starts with Exitstatus object*/
        return null ;
    }
}
