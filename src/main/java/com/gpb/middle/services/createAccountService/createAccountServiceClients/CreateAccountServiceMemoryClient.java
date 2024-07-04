package com.gpb.middle.services.createAccountService.createAccountServiceClients;

import com.gpb.middle.dto.request.CreateAccountDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.models.Account;
import com.gpb.middle.repo.AccountRepository;
import com.gpb.middle.repo.UserRepository;
import com.gpb.middle.services.createAccountService.CreateAccountServiceClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@ConditionalOnProperty(value="project.memory.enabled")
public class CreateAccountServiceMemoryClient implements CreateAccountServiceClient {

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    public CreateAccountServiceMemoryClient(UserRepository userRepository,
                                            AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Error> runRequest(Long id, CreateAccountDTO createAccountDTO) {
        var user = userRepository.findByUserId(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Вам нужно зарегистрироваться!",
                    "type", "400", "trace_id"));
        }

        var account = accountRepository.findByUserId(user.get().getUserId());

        if (account.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("У вас уже есть счёт!",
                    "type", "400", "trace_id"));
        }

        var newId = UUID.randomUUID();
        var newAccount = new Account();
        newAccount.setUserId(id);
        newAccount.setAccountName(createAccountDTO.getAccountName());
        newAccount.setAccountId(newId.toString());
        newAccount.setAmount(new BigDecimal("5000.00"));

        accountRepository.save(newAccount);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
