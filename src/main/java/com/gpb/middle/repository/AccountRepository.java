package com.gpb.middle.repository;

import com.gpb.middle.dto.response.AccountDTOForStub;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    List<AccountDTOForStub> getAccounts();

    void add(AccountDTOForStub accountDTO);

    Optional<AccountDTOForStub> findByUserId(Long id);
}
