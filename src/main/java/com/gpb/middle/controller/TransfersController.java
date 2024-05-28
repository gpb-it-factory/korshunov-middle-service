package com.gpb.middle.controller;

import com.gpb.middle.dto.request.CreateTransferDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/transfers")
public class TransfersController {

//    @PostMapping(path = "/")
//    public void createTransfer(@Valid @RequestBody CreateTransferDTO createTransferDTO) {
//
//    }
}
