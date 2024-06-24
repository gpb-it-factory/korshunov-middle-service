package com.gpb.middle.services.getUserAccountService;

import com.gpb.middle.dto.response.AccountDTO;
import com.gpb.middle.dto.response.AccountDTOForStub;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.dto.response.Result;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GetUserAccountService {

    private final GetUserAccountServiceClient getUserAccountServiceClient;

    public GetUserAccountService(GetUserAccountServiceClient getUserAccountServiceClient) {
        this.getUserAccountServiceClient = getUserAccountServiceClient;
    }

    public Result<AccountDTO, Error> getAccount(Long id) {
        var result = getUserAccountServiceClient.runRequest(id);

        if (result.getStatusCode().isSameCodeAs(HttpStatus.OK)) {
            return Result.response((AccountDTO) result.getBody());
        }

        return Result.error((Error) result.getBody());
    }
}
