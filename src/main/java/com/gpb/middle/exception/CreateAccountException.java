package com.gpb.middle.exception;

import com.gpb.middle.dto.response.Error;
import lombok.Getter;

@Getter
public class CreateAccountException extends RuntimeException {

    private final String code;

    public CreateAccountException(Error error) {
        super(error.getMessage());
        this.code = error.getCode();
    }
}
