package io.spring.batch.domain;


public class InvocationContext<T> {
    private static final long serialVersionUID = 7349998965655236L;
    int errorCode = 0;
    int errorStep = 0;
    T data;
    String[] errorParam = null;

    public InvocationContext(int errorCode, int errorStep) {
        this.errorCode = errorCode;
        this.errorStep = errorStep;
    }

    public InvocationContext(int errorCode, int errorStep, String[] errorParam) {
        this.errorCode = errorCode;
        this.errorStep = errorStep;
        this.errorParam = errorParam;
    }

    public InvocationContext() {
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode, int errorStep) {
        this.errorCode = errorCode;
        this.errorStep = errorStep;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorStep() {
        return this.errorStep;
    }

    public String toString() {
        return "InvocationContext{errorCode=" + this.errorCode + ", errorStep=" + this.errorStep + ", data=" + this.data + '}';
    }

    public boolean isSuccessful() {
        return this.errorCode == 0;
    }

    public String[] getErrorParam() {
        return this.errorParam;
    }

    public void setErrorParam(String[] errorParam) {
        this.errorParam = errorParam;
    }
}
