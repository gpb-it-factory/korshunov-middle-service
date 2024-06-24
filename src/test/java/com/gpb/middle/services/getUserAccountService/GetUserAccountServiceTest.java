package com.gpb.middle.services.getUserAccountService;

import com.gpb.middle.dto.response.AccountDTO;
import com.gpb.middle.dto.response.AccountDTOForStub;
import com.gpb.middle.dto.response.Error;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class GetUserAccountServiceTest {

    @Mock
    GetUserAccountServiceClient getUserAccountServiceClient;

    @InjectMocks
    GetUserAccountService getUserAccountService;

    @Test
    void testGetUserService_WithCorrectResponse() {
        var id = UUID.randomUUID();
        var response = ResponseEntity.status(200).body(new AccountDTO(id.toString(),
                "new",
                "5000.00"));
        Mockito.doReturn(response).when(getUserAccountServiceClient).runRequest(any());
        var result = getUserAccountService.getAccount(11L);

        Assertions.assertTrue(result.isResponse());
    }

    @Test
    void testGetUserService_WithErrorResponse() {
        var response = ResponseEntity.status(400).body(new Error("message",
                "type",
                "400",
                "trace_id"));
        Mockito.doReturn(response).when(getUserAccountServiceClient).runRequest(any());
        var result = getUserAccountService.getAccount(11L);

        Assertions.assertTrue(result.isError());
    }
}
