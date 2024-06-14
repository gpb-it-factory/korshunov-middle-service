package com.gpb.middle.services.userRegisterService;

import com.gpb.middle.dto.request.CreateUserDTO;

public interface UserRegisterServiceClientImpl {
    void runRequest(CreateUserDTO createUserDTO);
}
