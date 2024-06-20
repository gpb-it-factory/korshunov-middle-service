package com.gpb.middle.services.createAccountService;

import com.gpb.middle.dto.request.CreateAccountDTO;
import org.springframework.http.ResponseEntity;

public interface CreateAccountServiceClient {
    ResponseEntity<?> runRequest(Long id, CreateAccountDTO createAccountDTO);
}
