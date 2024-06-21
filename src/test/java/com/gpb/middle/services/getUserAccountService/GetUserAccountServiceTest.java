package com.gpb.middle.services.getUserAccountService;

import com.gpb.middle.dto.response.AccountDTO;
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

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class GetUserAccountServiceTest {

    @Mock
    GetUserAccountServiceClient getUserAccountServiceClient;

    @InjectMocks
    GetUserAccountService getUserAccountService;

    @Test
    void testGetUserService_WithCorrectResponse() {
        var response = ResponseEntity.status(200).body(new AccountDTO(11L,
                "new",
                new BigDecimal("5000.00")));
        Mockito.doReturn(response).when(getUserAccountServiceClient).runRequest(any());
        var result = getUserAccountService.getAccount(11L);

        Assertions.assertEquals(true, result.isResponse());
    }

    @Test
    void testGetUserService_WithErrorResponse() {
        var response = ResponseEntity.status(400).body(new Error("message",
                "type",
                "400",
                "trace_id"));
        Mockito.doReturn(response).when(getUserAccountServiceClient).runRequest(any());
        var result = getUserAccountService.getAccount(11L);

        Assertions.assertEquals(true, result.isError());
    }
}
