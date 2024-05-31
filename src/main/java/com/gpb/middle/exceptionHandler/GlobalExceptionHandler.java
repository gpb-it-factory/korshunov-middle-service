package com.gpb.middle.exceptionHandler;

import com.gpb.middle.exception.ErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<String> handlerErrorException(ErrorException error) {
        return ResponseEntity.status(error.getStatusCode()).body(error.toString());
    }
}
