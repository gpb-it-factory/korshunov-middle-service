package com.gpb.middle.services.transferService;

import com.gpb.middle.dto.request.CreateTransferDTO;
import org.springframework.http.ResponseEntity;

public interface TransferServiceClient {
    ResponseEntity<?> runRequest(CreateTransferDTO createTransferDTO);
}
