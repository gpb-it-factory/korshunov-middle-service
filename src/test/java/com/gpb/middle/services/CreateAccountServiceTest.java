package com.gpb.middle.services;

import com.gpb.middle.services.createAccountService.createAccountServiceClient.CreateAccountServiceHttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateAccountServiceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CreateAccountServiceHttpClient createAccountServiceHttpClient;

    @Test
    public void testCreateAccountWithCorrectData() throws Exception {
        var request = post("/v2/users/{id}/accounts", 12)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"accountName\":\"New account\"}");

        mockMvc.perform(request).andExpect(status().is(204));
    }

    @Test
    public void createAccountWithWrongData_blankAccountName() throws Exception {
        var request = post("/v2/users/{id}/accounts", 12)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"accountName\":\"\"}");

        mockMvc.perform(request).andExpect(status().is(400));
    }

    @Test
    public void testCreateAccountWithWrongData_NameWithLessThanThreeLetters() throws Exception {
        var request = post("/v2/users/{id}/accounts", 12)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"accountName\":\"a\"}");

        mockMvc.perform(request).andExpect(status().is(400));
    }
}
