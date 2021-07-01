package com.element.assignment.config;

import com.element.assignment.dto.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.net.BindException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomResponseEntityErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomResponseEntityErrorHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        if(ex instanceof MissingRequestValueException){
            return getErrorObject(HttpStatus.BAD_REQUEST, "Invalid request", ex);
        }
        return getErrorObject(HttpStatus.INTERNAL_SERVER_ERROR, "There is some issue with the service. Please reach out to the team.", ex);
    }

    @ExceptionHandler(IncorrectRequestException.class)
    @ResponseBody
    public ResponseEntity<Object> handleValidationException(IncorrectRequestException ex, WebRequest request) {
        return getErrorObject(HttpStatus.BAD_REQUEST, "Invalid request", ex);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<Object> handleUserNotFoundException(RuntimeException ex, WebRequest request) {
        if(ex instanceof DataNotFoundException){
            return getErrorObject(HttpStatus.NOT_FOUND, "Invalid request", ex);
        }
        return getErrorObject(HttpStatus.NOT_FOUND, "No such data available.", ex);
    }

    private ResponseEntity<Object> getErrorObject(HttpStatus status, String message, Exception ex){
        logger.error(ex.getLocalizedMessage());
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorMessage error = new ErrorMessage(message, details);
        return new ResponseEntity(error, status);
    }

}
