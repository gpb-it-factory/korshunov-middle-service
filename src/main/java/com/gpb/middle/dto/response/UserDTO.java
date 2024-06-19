package com.gpb.middle.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class UserDTO {

    private final Long userId;

    public UserDTO(Long userId) {
        this.userId = userId;
    }

}
