package com.authority.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ExceptionInfo> handleException(Throwable ex) {
            if (ex instanceof ServiceException) {
                ServiceException e = (ServiceException)ex;
                return new ResponseEntity<ExceptionInfo>(new ExceptionInfo(e.getMessage(), LocalDate.now()), e.getHttpStatus());
            }  else {
                return new ResponseEntity<ExceptionInfo>(new ExceptionInfo(ex.getMessage(), LocalDate.now()),HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
}
