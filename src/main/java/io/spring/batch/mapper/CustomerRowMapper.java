package io.spring.batch.mapper;

import io.spring.batch.domain.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

        Customer customer = new Customer();
        customer.setNationalCode(rs.getLong("NATIONAL_CODE"));
        customer.setCustomerState(rs.getShort("CUSTOMER_STATE"));
        customer.setCustomerType(rs.getShort("CUSTOMER_TYPE"));
        customer.setNationalCode(rs.getLong("NATIONAL_CODE"));
        return customer;

    }
}