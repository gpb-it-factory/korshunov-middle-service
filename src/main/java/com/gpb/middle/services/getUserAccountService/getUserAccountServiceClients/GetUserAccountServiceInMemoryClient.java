package com.gpb.middle.services.getUserAccountService.getUserAccountServiceClients;

import com.gpb.middle.dto.response.Error;
import com.gpb.middle.repository.AccountRepository;
import com.gpb.middle.repository.UserRepository;
import com.gpb.middle.services.getUserAccountService.GetUserAccountServiceClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value="project.memory.enabled")
public class GetUserAccountServiceInMemoryClient implements GetUserAccountServiceClient {

    private UserRepository userRepository;

    private AccountRepository accountRepository;

    public GetUserAccountServiceInMemoryClient(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public ResponseEntity<?> runRequest(Long id) {
        var user = userRepository.findById(id);

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
        return ResponseEntity.status(200).body(account.get());
    }
}
