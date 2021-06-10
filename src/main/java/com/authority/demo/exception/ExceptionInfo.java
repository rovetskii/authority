package com.authority.demo.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class ExceptionInfo {
    private String message;
    private LocalDate date;

    public ExceptionInfo(String message, LocalDate date) {
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getDate() {
        return date;
    }

}
