package com.gpb.middle.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class CreateUserDTO {

    @NotNull
    @Positive
    public Long userId;
}
