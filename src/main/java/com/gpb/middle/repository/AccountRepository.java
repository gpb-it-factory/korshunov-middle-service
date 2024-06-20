package com.gpb.middle.repository;

import com.gpb.middle.dto.response.AccountDTO;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    List<AccountDTO> getAccounts();

    void add(AccountDTO accountDTO);

    Optional<AccountDTO> findByUserId(Long id);
}
