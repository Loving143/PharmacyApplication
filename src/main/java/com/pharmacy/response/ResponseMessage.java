package com.pharmacy.response;

public class ResponseMessage<T> {
    private String status;
    private T data;

    public ResponseMessage(String message, T data) {
        this.status = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
