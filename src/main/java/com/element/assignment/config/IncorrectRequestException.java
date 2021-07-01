package com.element.assignment.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.BindException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectRequestException extends BindException {

    public IncorrectRequestException(String msg) {
        super(msg);
    }
}
