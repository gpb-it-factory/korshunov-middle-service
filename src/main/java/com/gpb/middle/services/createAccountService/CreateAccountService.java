package com.gpb.middle.services.createAccountService;

import com.gpb.middle.dto.request.CreateAccountDTO;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountService {

    private CreateAccountServiceClientImpl createAccountServiceClient;

    public CreateAccountService(CreateAccountServiceClientImpl createAccountServiceClient) {
        this.createAccountServiceClient = createAccountServiceClient;
    }

    public void exec(Long id, CreateAccountDTO createAccountDTO) {
        createAccountServiceClient.runRequest(id, createAccountDTO);

    }
}
