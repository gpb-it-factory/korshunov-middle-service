package com.gpb.middle.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateAccountDTO {

    @NotBlank
    @Length(min = 3, max = 30)
    private String accountName;
}
