package com.gpb.middle.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"userId", "accountName"})
public class AccountDTO {

    private Long userId;

    private String accountName;

    private BigDecimal amount;

    public AccountDTO(Long userId, String accountName) {
        this.userId = userId;
        this.accountName = accountName;
    }
}
