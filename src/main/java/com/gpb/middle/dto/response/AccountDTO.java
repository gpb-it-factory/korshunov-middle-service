package com.gpb.middle.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class AccountDTO {

    private Long userId;

    private String accountName;
}
