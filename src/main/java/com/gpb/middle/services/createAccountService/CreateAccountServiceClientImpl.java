package com.gpb.middle.services.createAccountService;

import com.gpb.middle.dto.request.CreateAccountDTO;

public interface CreateAccountServiceClientImpl {
    void runRequest(Long id, CreateAccountDTO createAccountDTO);
}
