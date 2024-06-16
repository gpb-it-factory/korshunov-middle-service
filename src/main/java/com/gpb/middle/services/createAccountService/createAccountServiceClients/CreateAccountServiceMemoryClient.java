package com.gpb.middle.services.createAccountService.createAccountServiceClients;

import com.gpb.middle.dto.request.CreateAccountDTO;
import com.gpb.middle.dto.response.AccountDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.services.createAccountService.CreateAccountServiceClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ConditionalOnProperty(value="project.memory.enabled")
public class CreateAccountServiceMemoryClient implements CreateAccountServiceClient {

    private final Set<AccountDTO> accounts;

    public CreateAccountServiceMemoryClient() {
        this.accounts = ConcurrentHashMap.newKeySet();
    }

    public boolean checkUser(AccountDTO accountDTO) {
        return accounts.contains(accountDTO);
    }

    public ResponseEntity<Error> runRequest(Long id, CreateAccountDTO createAccountDTO) {
        var accountDTO = new AccountDTO(id, createAccountDTO.getAccountName());
        if (!checkUser(accountDTO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Error("Вы уже зарегистрированы", "404"));
        };
        accounts.add(accountDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
