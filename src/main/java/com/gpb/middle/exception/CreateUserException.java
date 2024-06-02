package com.gpb.middle.exception;

import com.gpb.middle.dto.response.Error;
import lombok.Getter;

@Getter
public class CreateUserException extends RuntimeException {

    private final Error error;

    public CreateUserException(Error error) {
        super();
        this.error = error;
    }
}
