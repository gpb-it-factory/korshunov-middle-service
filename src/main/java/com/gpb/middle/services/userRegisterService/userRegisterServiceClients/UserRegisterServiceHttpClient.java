package com.gpb.middle.services.userRegisterService.userRegisterServiceClients;

import com.gpb.middle.dto.request.CreateUserDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.services.userRegisterService.UserRegisterServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@ConditionalOnProperty(value="project.http.enabled")
public class UserRegisterServiceHttpClient implements UserRegisterServiceClient {

    private final String path;

    private final RestClient restClient;

    public UserRegisterServiceHttpClient(@Value("${project.register.path}") String path) {
        this.path = path;
        this.restClient = RestClient.create();
    }

    public ResponseEntity<Error> runRequest(CreateUserDTO createUserDTO) {
        return restClient.post()
                .uri(path)
                .body(createUserDTO)
                .retrieve()
                .toEntity(Error.class);
    }
}
