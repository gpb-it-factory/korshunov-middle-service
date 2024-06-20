package com.gpb.middle.services.userRegisterService;

import com.gpb.middle.dto.request.CreateUserDTO;
import org.springframework.http.ResponseEntity;

public interface UserRegisterServiceClient {
    ResponseEntity<?> runRequest(CreateUserDTO createUserDTO);
}
