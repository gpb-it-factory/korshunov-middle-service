package com.gpb.middle.services.createAccountService.createAccountServiceClient;

import com.gpb.middle.dto.request.CreateAccountDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.exception.CreateAccountException;
import com.gpb.middle.services.createAccountService.CreateAccountServiceClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;

@Component
@ConditionalOnProperty(value="project.http.enabled")
public class CreateAccountServiceHttpClient implements CreateAccountServiceClientImpl {

    private String path;

    private RestClient restClient;

    public CreateAccountServiceHttpClient(@Value("${project.create_account.path}") String path) {
        this.path = path;
        this.restClient = RestClient.create();
    }

    @Override
    public void runRequest(Long id, CreateAccountDTO createAccountDTO) {
        try {
            var response = createAccount(id, createAccountDTO);

            if (!response.getStatusCode().isSameCodeAs(HttpStatus.NO_CONTENT)) {
                throw new CreateAccountException(response.getBody());
            }
        } catch (RestClientException error) {
            throw new CreateAccountException(new Error(error.getMessage(), "500"));
        }
    }

    public ResponseEntity<Error> createAccount(Long id, CreateAccountDTO createAccountDTO) {
        return restClient.post()
                .uri(path, id)
                .body(createAccountDTO)
                .retrieve()
                .toEntity(Error.class);
    }
}
