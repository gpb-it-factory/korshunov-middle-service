package com.gpb.middle.services.getUserAccountService.getUserAccountServiceClients;

import com.gpb.middle.dto.response.AccountDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.repo.AccountRepository;
import com.gpb.middle.repo.UserRepository;
import com.gpb.middle.services.getUserAccountService.GetUserAccountServiceClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value="project.memory.enabled")
public class GetUserAccountServiceInMemoryClient implements GetUserAccountServiceClient {

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    public GetUserAccountServiceInMemoryClient(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public ResponseEntity<?> runRequest(Long id) {
        var user = userRepository.findByUserId(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Вам нужно зарегистрироваться!",
                    "type",
                    "400",
                    "trace_id"));
        }

        var account = accountRepository.findByUserId(id);

        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("У вас нет счёта!",
                    "type",
                    "400",
                    "trace_id"));
        }

        var accountEntity = account.get();
        return ResponseEntity.status(200).body(new AccountDTO(
                accountEntity.getAccountId(),
                accountEntity.getAccountName(),
                accountEntity.getAmount().toString()));
    }
}
