package com.gpb.middle.services.getUserAccountService;

import org.springframework.http.ResponseEntity;

public interface GetUserAccountServiceClient {
    ResponseEntity<?> runRequest(Long id);
}
