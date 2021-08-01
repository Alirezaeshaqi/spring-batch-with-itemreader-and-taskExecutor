package io.spring.batch.domain;


import java.util.Date;

public class Customer {

    protected Long customerCode;
    protected short customerType;
    protected short customerState;
    protected long nationalCode;


    public Customer() {

    }

    public Customer(Long customerCode, short customerType, short customerState, Long nationalCode) {
        this.customerType = customerType;
        this.nationalCode = nationalCode;
        this.customerState = customerState;
        this.customerCode = customerCode;

    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public short getCustomerType() {
        return customerType;
    }

    public void setCustomerType(short customerType) {
        this.customerType = customerType;
    }

    public short getCustomerState() {
        return customerState;
    }

    public void setCustomerState(short customerState) {
        this.customerState = customerState;
    }

    public long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(long nationalCode) {
        this.nationalCode = nationalCode;
    }


    public void setDeathAnnouncmentDate(Integer deathAnnouncmentDate) {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerCode=" + customerCode +
                ", customerType=" + customerType +
                ", customerState=" + customerState +
                ", nationalCode=" + nationalCode +
                '}';
    }

}
