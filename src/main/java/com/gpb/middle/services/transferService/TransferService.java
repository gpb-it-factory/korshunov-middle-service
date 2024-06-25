package com.gpb.middle.services.transferService;

import com.gpb.middle.dto.request.CreateTransferDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.dto.response.Result;
import com.gpb.middle.dto.response.TransferDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    private final TransferServiceClient transferServiceClient;

    public TransferService(TransferServiceClient transferServiceClient) {
        this.transferServiceClient = transferServiceClient;
    }

    public Result<TransferDTO, Error> transfer(CreateTransferDTO createTransferDTO) {
        var result = transferServiceClient.runRequest(createTransferDTO);

        if (result.getStatusCode().isSameCodeAs(HttpStatus.OK)) {
            return Result.response((TransferDTO) result.getBody());
        }

        return Result.error((Error) result.getBody());
    }
}
