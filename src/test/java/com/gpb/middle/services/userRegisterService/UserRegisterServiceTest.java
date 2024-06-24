package com.gpb.middle.services.userRegisterService;

import com.gpb.middle.dto.request.CreateUserDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.exception.CreateAccountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegisterServiceTest {

    @Mock
    UserRegisterServiceClient userRegisterServiceClient;

    @InjectMocks
    UserRegisterService userRegisterService;

    @Test
    public void testUserRegisterService_throwExceptionOnStatusCodeNot204() {
        var response = ResponseEntity.status(400).body(new Error("new error",
                "type",
                "400",
                "trace_id"));
        Mockito.doReturn(response).when(userRegisterServiceClient).runRequest(any());

        Assertions.assertThrows(CreateAccountException.class,
                () -> userRegisterService.register(new CreateUserDTO(111L, "name")));
    }

    @Test
    public void errorReturnWithRestException() {
        Mockito.doThrow(RestClientException.class).when(userRegisterServiceClient).runRequest(any());

        Assertions.assertThrows(CreateAccountException.class,
                () -> userRegisterService.register(new CreateUserDTO(111L, "name")));
    }

    @Test
    public void errorReturnWithNoContentResponse() {
        var response = ResponseEntity.status(204).build();
        Mockito.doReturn(response).when(userRegisterServiceClient).runRequest(any());

        Assertions.assertDoesNotThrow(() -> userRegisterService.register(new CreateUserDTO(111L, "name")));
    }
}
