package com.gpb.middle.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateTransferDTO {

    @NotBlank
    private String from;

    @NotBlank
    private String to;

    @NotBlank
    private String amount;
}
