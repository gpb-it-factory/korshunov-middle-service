package com.gpb.middle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpb.middle.dto.request.CreateUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testUsersControllerCreateWithCorrectData() throws Exception {
        var user = new CreateUserDTO(88889L);
        var request = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user));

        mockMvc.perform(request)
                .andExpect(status().is(204));
    }

    @Test
    public void testUsersControllerCreateWithWrongData() throws Exception {
        var user = new CreateUserDTO(-1L);
        var request = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user));

        mockMvc.perform(request)
                .andExpect(status().is(400));
    }

    @Test
    public void testUsersControllerCreateWithCorrectDataSecondRepeatedRequest() throws Exception {
        var user = new CreateUserDTO(900065L);
        var request = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user));
        mockMvc.perform(request);
        mockMvc.perform(request).andExpect(status().is(400));
    }
}
