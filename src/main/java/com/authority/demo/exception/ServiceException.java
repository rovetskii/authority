package com.authority.demo.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception{

    private HttpStatus httpStatus;


    public ServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
