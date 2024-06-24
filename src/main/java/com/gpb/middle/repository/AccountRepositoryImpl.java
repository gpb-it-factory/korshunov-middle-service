package com.gpb.middle.repository;

import com.gpb.middle.dto.response.AccountDTOForStub;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
@Getter
public class AccountRepositoryImpl implements AccountRepository{

    private List<AccountDTOForStub> accounts = new CopyOnWriteArrayList<>();

    @Override
    public List<AccountDTOForStub> getAccounts() {
        return accounts;
    }

    @Override
    public void add(AccountDTOForStub accountDTO) {
        accounts.add(accountDTO);
    }

    @Override
    public Optional<AccountDTOForStub> findByUserId(Long id) {
        return accounts.stream()
                .filter(account -> account.getUserId() == id)
                .findFirst();
    }
}
