package com.gpb.middle.exceptionHandler;

import com.gpb.middle.exception.CreateAccountException;
import com.gpb.middle.exception.CreateUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CreateUserException.class)
    public ResponseEntity<String> handlerCreateUserException(CreateUserException exception) {
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.valueOf(Integer.parseInt(exception.getCode())));
    }
    
    @ExceptionHandler(CreateAccountException.class)
    public ResponseEntity<String> handlerCreateUserException(CreateAccountException exception) {
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.valueOf(Integer.parseInt(exception.getCode())));
    }
}
