package io.spring.batch.services;

import io.spring.batch.proccessor.FilteringItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Configurable
@Component
public class Sequences {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Integer getCustomerSequence() {
        String sql = " SELECT NEXT VALUE FOR CORE.SEQ_CUSTOMER FROM SYSIBM.SYSDUMMY1  ";
        int sequenceNumber = jdbcTemplate.queryForObject(sql, Integer.class);
        return sequenceNumber ;
    }

    public Integer getCustomerHistorySequence() {
        String sql = " SELECT NEXT VALUE FOR CORE.SEQ_TCS_CUSTOMER_HISTORY FROM SYSIBM.SYSDUMMY1  ";
        int sequenceNumber = jdbcTemplate.queryForObject(sql, Integer.class);
        return sequenceNumber ;
    }

    public Integer getContactSequence() {
        String sql = " SELECT NEXT VALUE FOR CORE.SEQ_TCS_CONTACTS FROM SYSIBM.SYSDUMMY1  ";
        int sequenceNumber = jdbcTemplate.queryForObject(sql, Integer.class);
        return sequenceNumber ;
    }

    public Integer getIndividualEducationSequence() {
        String sql = " SELECT NEXT VALUE FOR CORE.SEQ_TCS_INDIVIDUAL_EDUCATIONS FROM SYSIBM.SYSDUMMY1  ";
        int sequenceNumber = jdbcTemplate.queryForObject(sql, Integer.class);
        return  sequenceNumber ;
    }

    public Integer getJobSequence() {
        String sql = " SELECT NEXT VALUE FOR CORE.SEQ_TCS_JOBS FROM SYSIBM.SYSDUMMY1 ";
        int sequenceNumber = jdbcTemplate.queryForObject(sql, Integer.class);
        return  sequenceNumber ;
    }

    public Integer getIndividualHistoryId() {
        String sql = " SELECT NEXT VALUE FOR CORE.SEQ_TCS_INDIVIDUAL_HISTORY FROM SYSIBM.SYSDUMMY1 ";
        int sequenceNumber = jdbcTemplate.queryForObject(sql, Integer.class);
        return  sequenceNumber ;
    }
    /*--------------------------------------------------------------------------*/
    /*alter sequence*/

/*    public void alterSequenceCustomer() {
        String sql = " ALTER SEQUENCE CORE.SEQ_CUSTOMER RESTART WITH " + FilteringItemProcessor.CustomerSequence + " ";
        jdbcTemplate.update(sql);
    }

    public void alterSequenceContacts() {
        String sql = " ALTER SEQUENCE CORE.SEQ_TCS_CONTACTS RESTART WITH " + FilteringItemProcessor.ContactSequence + " ";
        jdbcTemplate.update(sql);
    }

    public void alterSequenceIndividualEducations() {
        String sql = " ALTER SEQUENCE CORE.SEQ_TCS_INDIVIDUAL_EDUCATIONS RESTART WITH " + FilteringItemProcessor.IndividualEducationSequence + " ";
        jdbcTemplate.update(sql);
    }

    public void alterSequenceJob() {
        String sql = " ALTER SEQUENCE CORE.SEQ_TCS_JOBS RESTART WITH " + FilteringItemProcessor.JobSequence + " ";
        jdbcTemplate.update(sql);
    }*/

}
