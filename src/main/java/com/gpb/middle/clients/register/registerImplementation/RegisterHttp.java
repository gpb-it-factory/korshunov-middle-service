package com.gpb.middle.clients.register.registerImplementation;

import com.gpb.middle.dto.request.CreateUserDTO;
import com.gpb.middle.dto.response.Error;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConditionalOnProperty(value="project.register.http.enabled")
public class RegisterHttp {

    private final String path;

    public RegisterHttp(@Value("${project.register.path}") String path) {
        this.path = path;
    }

    public ResponseEntity<Error> runRequest(CreateUserDTO createUserDTO) {
        HttpEntity<CreateUserDTO> createUserDTOHttpEntity = new HttpEntity<>(createUserDTO);
        return new RestTemplate().postForEntity(path, createUserDTOHttpEntity, Error.class);
    }
}
