package com.pharmacy.response;

import java.util.Objects;

public class ResponseMessage<T> {
    private String status;
    private T data;

    public ResponseMessage(String message, T data) {
        this.status = message;
        this.data = data;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ResponseMessage that = (ResponseMessage) obj;
        return Objects.equals(data, that.data) &&
               Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, data);
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
