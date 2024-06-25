package com.gpb.middle.services.transferService.transferServiceClients;

import com.gpb.middle.dto.request.CreateTransferDTO;
import com.gpb.middle.dto.response.TransferDTO;
import com.gpb.middle.services.transferService.TransferServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@ConditionalOnProperty(value="project.http.enabled")
public class TransferServiceHttpClient implements TransferServiceClient {

    private final String path;

    private final RestClient restClient;

    public TransferServiceHttpClient(@Value("${project.transfer.path}") String path) {
        this.path = path;
        this.restClient = RestClient.create();
    }


    @Override
    public ResponseEntity<?> runRequest(CreateTransferDTO createTransferDTO) {
        return restClient.post()
                .uri(path)
                .body(createTransferDTO)
                .exchange((request, response) -> {
                    var statusCode = response.getStatusCode();
                    if (statusCode.isSameCodeAs(HttpStatus.OK)) {
                        var body = response.bodyTo(TransferDTO.class);
                        return ResponseEntity.status(statusCode).body(body);
                    }
                    var body = response.bodyTo(Error.class);
                    return ResponseEntity.status(statusCode).body(body);
                });
    }
}
