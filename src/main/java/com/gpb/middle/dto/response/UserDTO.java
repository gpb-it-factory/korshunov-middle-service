package com.gpb.middle.dto.response;

import lombok.Getter;

@Getter
public class UserDTO {

    private String userId;

    public UserDTO(String userId) {
        this.userId = userId;
    }
}
