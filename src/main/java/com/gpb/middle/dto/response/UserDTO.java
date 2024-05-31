package com.gpb.middle.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class UserDTO {

    private final String userId;

    public UserDTO(String userId) {
        this.userId = userId;
    }
}
