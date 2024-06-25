package com.gpb.middle.controller;

import com.gpb.middle.dto.request.CreateTransferDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.dto.response.Result;
import com.gpb.middle.dto.response.TransferDTO;
import com.gpb.middle.services.transferService.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v2/transfer")
public class TransferController {

    private final TransferService transferService;


    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Result<TransferDTO, Error>> transfer(@Valid @RequestBody CreateTransferDTO createTransferDTO) {
        var result = transferService.transfer(createTransferDTO);
        return ResponseEntity.status(200).body(result);
    }
}
