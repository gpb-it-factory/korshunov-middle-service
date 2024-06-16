package com.gpb.middle.services.userRegisterService;

import com.gpb.middle.dto.request.CreateUserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {

    private final UserRegisterServiceClient userRegisterServiceClient;

    public UserRegisterService(UserRegisterServiceClient userRegisterServiceClient) {
        this.userRegisterServiceClient = userRegisterServiceClient;
    }

    public void exec(CreateUserDTO createUserDTO) {
        userRegisterServiceClient.runRequest(createUserDTO);
    }
}