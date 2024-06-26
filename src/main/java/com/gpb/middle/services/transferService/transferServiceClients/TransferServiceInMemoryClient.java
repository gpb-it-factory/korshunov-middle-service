package com.gpb.middle.services.transferService.transferServiceClients;

import com.gpb.middle.dto.request.CreateTransferDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.dto.response.TransferDTO;
import com.gpb.middle.repo.AccountRepository;
import com.gpb.middle.repo.UserRepository;
import com.gpb.middle.services.transferService.TransferServiceClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@ConditionalOnProperty(value="project.memory.enabled")
public class TransferServiceInMemoryClient implements TransferServiceClient {

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    public TransferServiceInMemoryClient(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public ResponseEntity<?> runRequest(CreateTransferDTO createTransferDTO) {

        var fromUser = userRepository.findByUserName(createTransferDTO.getFrom());
        if (fromUser.isEmpty()) {
            var error = new Error("Вы не зарегистрированы!", "type", "400", "trace_id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        var toUser = userRepository.findByUserName(createTransferDTO.getTo());
        if (toUser.isEmpty()) {
            var error = new Error("Получатель не найден!", "type", "400", "trace_id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        var accountFromUser = accountRepository.findByUserId(fromUser.get().getUserId());
        if (accountFromUser.isEmpty()) {
            var error = new Error("У вас нет счёта!", "type", "400", "trace_id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        var accountToUser = accountRepository.findByUserId(toUser.get().getUserId());
        if (accountToUser.isEmpty()) {
            var error = new Error("У получателя нет счёта!", "type", "400", "trace_id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        var amount = accountFromUser.get().getAmount();
        var amountForTransfer = new BigDecimal(createTransferDTO.getAmount());
        if (amount.subtract(amountForTransfer).floatValue() < 0) {
            var error = new Error("На вашем счёте недостаточно средств!",
                    "type",
                    "400",
                    "trace_id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        var accountFrom = accountFromUser.get();
        accountFrom.setAmount(amount.subtract(amountForTransfer));
        accountRepository.save(accountFrom);

        var accountTo = accountToUser.get();
        accountTo.setAmount(accountTo.getAmount().add(amountForTransfer));
        accountRepository.save(accountTo);

        var operationId = UUID.randomUUID();
        return ResponseEntity.status(HttpStatus.OK).body(new TransferDTO(operationId.toString()));
    }
}
