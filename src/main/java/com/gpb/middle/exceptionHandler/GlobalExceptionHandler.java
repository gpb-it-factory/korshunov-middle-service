package com.gpb.middle.exceptionHandler;

import com.gpb.middle.dto.response.Error;
import com.gpb.middle.exception.CreateUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CreateUserException.class)
    public ResponseEntity<Error> handlerCreateUserException(CreateUserException exception) {
        return new ResponseEntity<>(
                exception.getError(),
                HttpStatus.valueOf(Integer.parseInt(exception.getError().getCode())));
    }
}
