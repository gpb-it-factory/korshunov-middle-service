package com.gpb.middle.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class Error {

    public Error(String message, String code) {
        this.message = message;
        this.code = code;
    }

    private String message;

    private String type;

    private String code;

    private String trace_id;
}
