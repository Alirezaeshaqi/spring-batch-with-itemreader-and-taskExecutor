package io.spring.batch.domain;

import java.util.List;

public class ItemWriterDto {

    /*MODELS*/
    private Customer customer;
    private Integer deleteJobs;

    /*VARIABLES*/
    private Long nationalCode;
    private short updateNum;
    private boolean updateFlag = false;
    private int customerId;

    /*-------------------------------------------------------*/


    public Integer getDeleteJobs() {
        return deleteJobs;
    }

    public void setDeleteJobs(Integer deleteJobs) {
        this.deleteJobs = deleteJobs;
    }

    public boolean isUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(boolean updateFlag) {
        this.updateFlag = updateFlag;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public short getUpdateNum() {
        return updateNum;
    }

    public void setUpdateNum(short updateNum) {
        this.updateNum = updateNum;
    }

    public Long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(Long nationalCode) {
        this.nationalCode = nationalCode;
    }


    public ItemWriterDto(Customer customer) {
        this.customer = customer;
    }

    public ItemWriterDto() {

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
