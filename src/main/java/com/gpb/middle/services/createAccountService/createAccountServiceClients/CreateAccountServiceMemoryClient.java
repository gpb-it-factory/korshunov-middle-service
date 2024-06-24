package com.gpb.middle.services.createAccountService.createAccountServiceClients;

import com.gpb.middle.dto.request.CreateAccountDTO;
import com.gpb.middle.dto.response.AccountDTOForStub;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.repository.AccountRepository;
import com.gpb.middle.repository.UserRepository;
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

    private AccountRepository accountRepository;

    private UserRepository userRepository;

    public CreateAccountServiceMemoryClient(AccountRepository accountRepository,
                                            UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Error> runRequest(Long id, CreateAccountDTO createAccountDTO) {
        var user = userRepository.findById(id);

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
        var accountDTO = new AccountDTOForStub(id ,
                newId.toString(),
                createAccountDTO.getAccountName(),
                new BigDecimal("5000.00"));
        accountRepository.add(accountDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
