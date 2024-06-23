package com.gpb.middle.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountDTO {

    private String accountId;

    private String accountName;

    private String amount;
}
