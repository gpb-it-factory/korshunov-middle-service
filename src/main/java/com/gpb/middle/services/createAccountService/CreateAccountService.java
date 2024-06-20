package com.gpb.middle.services.createAccountService;

import com.gpb.middle.dto.request.CreateAccountDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.exception.CreateAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service
public class CreateAccountService {

    private final CreateAccountServiceClient createAccountServiceClient;

    public CreateAccountService(CreateAccountServiceClient createAccountServiceClient) {
        this.createAccountServiceClient = createAccountServiceClient;
    }

    public void create(Long id, CreateAccountDTO createAccountDTO) {
        try {
            var response = createAccountServiceClient.runRequest(id, createAccountDTO);

            if (!response.getStatusCode().isSameCodeAs(HttpStatus.NO_CONTENT)) {
                throw new CreateAccountException((Error) response.getBody());
            }
        } catch (RestClientException error) {
            throw new CreateAccountException(new Error(error.getMessage(), "500"));
        }
    }
}
