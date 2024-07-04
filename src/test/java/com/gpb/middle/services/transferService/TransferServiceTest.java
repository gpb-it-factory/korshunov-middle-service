package com.gpb.middle.services.transferService;

import com.gpb.middle.dto.request.CreateTransferDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.dto.response.TransferDTO;
import com.gpb.middle.services.transferService.transferServiceClients.TransferServiceHttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class TransferServiceTest {

    @Mock
    TransferServiceHttpClient transferServiceHttpClient;

    @InjectMocks
    TransferService transferService;

    @Test
    void testGetUserService_WithCorrectResponse() {
        var createTransferDTO = new CreateTransferDTO("fromme", "toyou", "200.00");
        var transferDTO = new TransferDTO(UUID.randomUUID().toString());
        var response = ResponseEntity.status(HttpStatus.OK).body(transferDTO);
        Mockito.doReturn(response).when(transferServiceHttpClient).runRequest(any());

        var result = transferService.transfer(createTransferDTO);

        Assertions.assertTrue(result.isResponse());
    }

    @Test
    void testGetUserService_WithError() {
        var createTransferDTO = new CreateTransferDTO("fromme", "toyou", "200.00");
        var error = new Error("message", "type", "400", "trace_id");
        var response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        Mockito.doReturn(response).when(transferServiceHttpClient).runRequest(any());

        var result = transferService.transfer(createTransferDTO);

        Assertions.assertTrue(result.isError());
    }
}
