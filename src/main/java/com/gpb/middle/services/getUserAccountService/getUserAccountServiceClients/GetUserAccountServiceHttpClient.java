package com.gpb.middle.services.getUserAccountService.getUserAccountServiceClients;

import com.gpb.middle.dto.response.AccountDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.services.getUserAccountService.GetUserAccountServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@ConditionalOnProperty(value="project.http.enabled")
public class GetUserAccountServiceHttpClient implements GetUserAccountServiceClient {

    private final String path;

    private final RestClient restClient;

    public GetUserAccountServiceHttpClient(@Value("${project.get_user_accounts.path}") String path) {
        this.path = path;
        this.restClient = RestClient.create();
    }

    @Override
    public ResponseEntity<?> runRequest(Long id) {
        return restClient.get()
                .uri(path, id)
                .exchange((request, response) -> {
                    if (response.getStatusCode().isSameCodeAs(HttpStatus.OK)) {
                        var body = response.bodyTo(new ParameterizedTypeReference<List<AccountDTO>>() {});
                        return ResponseEntity.status(response.getStatusCode()).body(body.get(0));
                    }
                    var body = response.bodyTo(Error.class);
                    return ResponseEntity.status(response.getStatusCode()).body(body);
                });
    }
}
