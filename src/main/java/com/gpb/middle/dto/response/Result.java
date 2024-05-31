package com.gpb.middle.dto.response;

import lombok.Getter;

@Getter
public final class Result {

    private UserDTO userDTO;

    private Error error;

}
