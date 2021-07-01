package com.element.assignment.dto;

import java.io.Serializable;
import java.util.List;


public class ErrorMessage implements Serializable {


    public ErrorMessage(String message, List<String> data) {
        super();
        this.message = message;
        this.data = data;
    }

    private String message;
    private List<String> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
