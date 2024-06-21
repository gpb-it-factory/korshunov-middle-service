package com.gpb.middle.services.userRegisterService;

import com.gpb.middle.dto.request.CreateUserDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.exception.CreateAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;

@Service
public class UserRegisterService {

    private final UserRegisterServiceClient userRegisterServiceClient;

    public UserRegisterService(UserRegisterServiceClient userRegisterServiceClient) {
        this.userRegisterServiceClient = userRegisterServiceClient;
    }

    public void register(CreateUserDTO createUserDTO) {
        try {
            var response = userRegisterServiceClient.runRequest(createUserDTO);

            if (!response.getStatusCode().isSameCodeAs(HttpStatus.NO_CONTENT)) {
                throw new CreateAccountException((Error) response.getBody());
            }
        } catch (RestClientException error) {
            throw new CreateAccountException(new Error(error.getMessage(),
                    error.getClass().toString(),
                    "500",
                    Arrays.toString(error.getStackTrace())));
        }
    }
}