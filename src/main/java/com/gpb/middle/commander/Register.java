package com.gpb.middle.commander;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpb.middle.dto.request.CreateUserDTO;
import com.gpb.middle.dto.response.Result;
import com.gpb.middle.dto.response.UserDTO;
import org.apache.catalina.User;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Register {

    private final RestTemplate restTemplate;

    public Register(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Object exec(CreateUserDTO createUserDTO) {
        HttpEntity<CreateUserDTO> entity = new HttpEntity<>(createUserDTO);
        ResponseEntity<Object> response = restTemplate.postForEntity(
                "http://localhost:8082:/register",
                entity,
                Object.class);
        return response.getBody();
    }
}