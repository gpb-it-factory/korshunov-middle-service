package com.gpb.middle.services;

import com.gpb.middle.services.userRegisterService.userRegisterServiceClient.UserRegisterServiceHttpClient;
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
public class CreateUserServiceTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public UserRegisterServiceHttpClient userRegisterServiceHttpClient;

    @Test
    public void testCreateUserWithCorrectData() throws Exception {
        var request = post("/v2/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":\"133\"}");

        mockMvc.perform(request).andExpect(status().is(204));
    }

    @Test
    public void createAccountWithWrongData_negativeDigit() throws Exception {
        var request = post("/v2/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":\"-2\"}");

        mockMvc.perform(request).andExpect(status().is(400));
    }

    @Test
    public void createAccountWithWrongData_blankUserId() throws Exception {
        var request = post("/v2/users/{id}/accounts", 12)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":\"\"}");

        mockMvc.perform(request).andExpect(status().is(400));
    }
}
