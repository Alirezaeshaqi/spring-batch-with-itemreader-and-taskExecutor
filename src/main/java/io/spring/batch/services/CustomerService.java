package io.spring.batch.services;


import io.spring.batch.domain.*;
import io.spring.batch.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class CustomerService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Sequences sequences;

    public boolean insertCustomer(Customer customer) {
        try {
            String sql = "INSERT INTO CIF.TCS_CUSTOMER " +
                    "(" +
                    " CUSTOMER_CODE " +
                    ",CUSTOMER_TYPE " +
                    ",CITIZENSHIP_TYPE " +
                    ",NATIONAL_CODE " +
                    ")" +
                    " VALUES (?,?,?,?)";

            jdbcTemplate.update(sql
                    ,
                    new Object[]{customer.getCustomerCode(), customer.getCustomerCode(), customer.getCustomerType(), customer.getNationalCode()}
            );
            return true;
        } catch (Exception e) {
            customer.toString();
            e.printStackTrace();
            return false;
        }
    }

    public Customer fetchCustomerByNationalCode(Long national_code) {
        try {
            String sql = "  SELECT ID ," +
                    "CITIZENSHIP_TYPE, " +
                    "CUSTOMER_STATE," +
                    "CUSTOMER_TYPE, " +
                    "DEATH_CONFIRMATION," +
                    "GENDER, " +
                    "IS_ACTIVE," +
                    "IS_ALIVE, " +
                    "IS_INQUIRY_APPROVED," +
                    "BIRTH_DATE, " +
                    "CREATE_CHANNEL_ID," +
                    "CREATE_DATE, " +
                    "CREATE_UNIT_ID,  " +
                    "CREATE_USER_ID, " +
                    "DEATH_DATE," +
                    "EDIT_DATE, " +
                    "EDIT_USER_ID," +
                    "NATIONALITY_ID," +
                    " REGISTRATION_CITY_ID,  " +
                    "REGISTRATION_DATE,  " +
                    "CUSTOMER_CODE, " +
                    "REGISTRATION_NUMBER,  " +
                    "FORMER_NATIONAL_CODE,  " +
                    "NATIONAL_CODE," +
                    " SHAHAB_CODE,  " +
                    "FATHER_NAME, " +
                    "FIRST_NAME,  " +
                    "LAST_NAME, " +
                    "MIDDLE_NAME," +
                    "IS_MIGRATED   FROM CIF.TCS_CUSTOMER WHERE NATIONAL_CODE = ? ";

            Customer customer = jdbcTemplate.queryForObject(sql, new Object[]{national_code}, new CustomerRowMapper());
            return customer;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
