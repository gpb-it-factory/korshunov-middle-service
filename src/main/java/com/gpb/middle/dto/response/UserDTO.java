package com.gpb.middle.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public final class UserDTO {

    private final Long userId;

    private final String userName;
}
