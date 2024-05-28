package com.gpb.middle.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class CreateTransferDTO {

    @NotNull
    @Positive
    public Long fromAccount;

    @NotNull
    @Positive
    public Long toAccount;

    @NotNull
    @Positive
    public Long amount;
}
