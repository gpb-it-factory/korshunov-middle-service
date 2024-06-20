package com.gpb.middle.repository;

import com.gpb.middle.dto.response.AccountDTO;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
@Getter
public class AccountRepositoryImpl implements AccountRepository{

    List<AccountDTO> accounts = new CopyOnWriteArrayList<>();

    @Override
    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    @Override
    public void add(AccountDTO accountDTO) {
        accounts.add(accountDTO);
    }

    @Override
    public Optional<AccountDTO> findByUserId(Long id) {
        return accounts.stream()
                .filter(account -> account.getUserId() == id)
                .findFirst();
    }
}
