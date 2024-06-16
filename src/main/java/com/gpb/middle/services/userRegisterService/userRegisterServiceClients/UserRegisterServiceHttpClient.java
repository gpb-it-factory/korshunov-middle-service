package com.gpb.middle.services.userRegisterService.userRegisterServiceClients;

import com.gpb.middle.services.userRegisterService.UserRegisterServiceClient;
import com.gpb.middle.dto.request.CreateUserDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.exception.CreateUserException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConditionalOnProperty(value="project.http.enabled")
public class UserRegisterServiceHttpClient implements UserRegisterServiceClient {

    private final String path;

    public UserRegisterServiceHttpClient(@Value("${project.register.path}") String path) {
        this.path = path;
    }

    public void runRequest(CreateUserDTO createUserDTO) {
        HttpEntity<CreateUserDTO> createUserDTOHttpEntity = new HttpEntity<>(createUserDTO);
        var result = new RestTemplate().postForEntity(path, createUserDTOHttpEntity, Error.class);
        if (result.getStatusCode().isError()) {
            throw new CreateUserException(result.getBody());
        }
    }
}
