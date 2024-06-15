package com.gpb.middle.services.createAccountService.createAccountServiceClient;

import com.gpb.middle.dto.request.CreateAccountDTO;
import com.gpb.middle.dto.response.AccountDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.exception.CreateAccountException;
import com.gpb.middle.services.createAccountService.CreateAccountServiceClientImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ConditionalOnProperty(value="project.memory.enabled")
public class CreateAccountServiceMemoryClient implements CreateAccountServiceClientImpl {

    private final Set<AccountDTO> accounts;

    public CreateAccountServiceMemoryClient() {
        this.accounts = ConcurrentHashMap.newKeySet();
    }

    public void checkUser(AccountDTO accountDTO) {
        if (accounts.contains(accountDTO)) {
            var error = new Error("message", "type", "400", "trace_id");
            throw new CreateAccountException(error);
        };
    }

    public void runRequest(Long id, CreateAccountDTO createAccountDTO) {
        var accountDTO = new AccountDTO(id, createAccountDTO.getAccountName());
        checkUser(accountDTO);
        accounts.add(accountDTO);
    }
}
