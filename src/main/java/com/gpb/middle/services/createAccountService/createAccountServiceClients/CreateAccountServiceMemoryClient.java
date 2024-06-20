package com.gpb.middle.services.createAccountService.createAccountServiceClients;

import com.gpb.middle.dto.request.CreateAccountDTO;
import com.gpb.middle.dto.response.AccountDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.repository.AccountRepository;
import com.gpb.middle.repository.UserRepository;
import com.gpb.middle.services.createAccountService.CreateAccountServiceClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value="project.memory.enabled")
public class CreateAccountServiceMemoryClient implements CreateAccountServiceClient {

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    public CreateAccountServiceMemoryClient(AccountRepository accountRepository,
                                            UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Error> runRequest(Long id, CreateAccountDTO createAccountDTO) {
        var accountDTO = new AccountDTO(id, createAccountDTO.getAccountName());
        var user = userRepository.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Вам нужно зарегистрироваться!",
                    "400"));
        }
        if (accountRepository.getAccounts().contains(accountDTO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("У вас уже есть счёт!",
                    "400"));
        }

        accountRepository.add(accountDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
