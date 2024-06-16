package com.gpb.middle.services.createAccountService;

import com.gpb.middle.dto.request.CreateAccountDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.exception.CreateAccountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class CreateAccountServiceTest {

    @Mock
    CreateAccountServiceClient createAccountServiceClient;

    @InjectMocks
    CreateAccountService createAccountService;

    @Test
    public void errorReturnWithCreateAccountException() {
        var response = ResponseEntity.status(400).body(new Error("new error", "400"));
        Mockito.doReturn(response).when(createAccountServiceClient).runRequest(any(), any());

        Assertions.assertThrows(CreateAccountException.class,
                () -> createAccountService.exec(1L, new CreateAccountDTO("name")));
    }

    @Test
    public void errorReturnWithRestException() {
        Mockito.doThrow(RestClientException.class).when(createAccountServiceClient).runRequest(any(), any());

        Assertions.assertThrows(CreateAccountException.class,
                () -> createAccountService.exec(1L, new CreateAccountDTO("name")));
    }

    @Test
    public void errorReturnWithNoContentResponse() {
        var response = ResponseEntity.status(204).build();
        Mockito.doReturn(response).when(createAccountServiceClient).runRequest(any(), any());

        Assertions.assertDoesNotThrow(() -> createAccountService.exec(1L, new CreateAccountDTO("name")));
    }
}
