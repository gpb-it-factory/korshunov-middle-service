package com.gpb.middle.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateAccountDTO {

    @NotBlank
    public String accountName;
}
