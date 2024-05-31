package com.gpb.middle.exception;

import com.gpb.middle.dto.response.Error;
import lombok.Getter;

@Getter
public class CreateUserException extends RuntimeException {

    private final int statusCode;

    private final String type;

    private final String trace_id;

    public CreateUserException(Error error) {
        super(error.getMessage());
        this.statusCode = Integer.getInteger(error.getCode());
        this.type = error.getType();
        this.trace_id = error.getTrace_id();
    }

    @Override
    public String toString() {
        return String.format(
                "Message: %s\nType: %s\nStatus: %d\nTrace id: %s",
                getMessage(),
                type,
                statusCode,
                trace_id);
    }
}
