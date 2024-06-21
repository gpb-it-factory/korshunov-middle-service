package com.gpb.middle.services.createAccountService.createAccountServiceClients;

import com.gpb.middle.dto.request.CreateAccountDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.services.createAccountService.CreateAccountServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@ConditionalOnProperty(value="project.http.enabled")
public class CreateAccountServiceHttpClient implements CreateAccountServiceClient {

    private final String path;

    private final RestClient restClient;

    public CreateAccountServiceHttpClient(@Value("${project.create_account.path}") String path) {
        this.path = path;
        this.restClient = RestClient.create();
    }

    public ResponseEntity<?> runRequest(Long id, CreateAccountDTO createAccountDTO) {
        return restClient.post()
                .uri(path, id)
                .body(createAccountDTO)
                .retrieve()
                .toEntity(Error.class);
    }
}
