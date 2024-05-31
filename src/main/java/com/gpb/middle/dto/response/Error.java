package com.gpb.middle.dto.response;

import lombok.Getter;

@Getter
public final class Error {

    private String message;

    private String type;

    private String code;

    private String trace_id;
}
