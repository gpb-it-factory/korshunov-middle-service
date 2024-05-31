package com.gpb.middle.exceptionHandler;

import com.gpb.middle.exception.CreateUserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CreateUserException.class)
    public ResponseEntity<String> handlerErrorException(CreateUserException error) {
        return ResponseEntity.status(error.getStatusCode()).body(error.toString());
    }
}
