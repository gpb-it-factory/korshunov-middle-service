package com.gpb.middle.services.userRegisterService;

import com.gpb.middle.dto.request.CreateUserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {

    private final UserRegisterServiceClientImpl userRegisterServiceClient;

    public UserRegisterService(UserRegisterServiceClientImpl userRegisterServiceClient) {
        this.userRegisterServiceClient = userRegisterServiceClient;
    }

    public void exec(CreateUserDTO createUserDTO) {
        userRegisterServiceClient.runRequest(createUserDTO);
    }
}