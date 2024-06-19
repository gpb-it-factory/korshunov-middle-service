package com.gpb.middle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpb.middle.dto.request.CreateAccountDTO;
import com.gpb.middle.dto.request.CreateUserDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.exception.CreateAccountException;
import com.gpb.middle.services.createAccountService.CreateAccountService;
import com.gpb.middle.services.userRegisterService.UserRegisterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTests {

//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @MockBean
//    CreateAccountService createAccountService;
//
//    @MockBean
//    UserRegisterService userRegisterService;
//
//    @Test
//    public void testUserController_CorrectAnswerOnCreateAccountWithCorrectData() throws Exception {
//        var body = new CreateAccountDTO("name");
//        var request = post("/v2/users/{id}/accounts", 11)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(body));
//
//        mockMvc.perform(request).andExpect(status().is(204));
//    }
//
//    @Test
//    public void testUserController_CorrectAnswerOnCreateAccountWithWrongData() throws Exception {
//        var body = new CreateAccountDTO("name");
//        var request = post("/v2/users/{id}/accounts", 11)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(body));
//
//        Mockito.doThrow(new CreateAccountException(new Error("message", "404")))
//                        .when(createAccountService).create(any(), any());
//
//        mockMvc.perform(request).andExpect(status().is(404));
//    }
//
//    @Test
//    public void testUserController_CorrectAnswerOnCreateUserRegisterWithCorrectData() throws Exception {
//        var body = new CreateUserDTO(111L, "name");
//        var request = post("/v2/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(body));
//
//        mockMvc.perform(request).andExpect(status().is(204));
//    }
//
//    @Test
//    public void testUserController_CorrectAnswerOnUserRegisterWithWrongData() throws Exception {
//        var body = new CreateUserDTO(111L, "name");
//        var request = post("/v2/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(body));
//
//        Mockito.doThrow(new CreateAccountException(new Error("message", "404")))
//                .when(userRegisterService).register(any());
//
//        mockMvc.perform(request).andExpect(status().is(404));
//    }
//
//    @Test
//    public void testCreateAccount_WithCorrectData() throws Exception {
//        var request = post("/v2/users/{id}/accounts", 12)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"accountName\":\"New account\"}");
//
//        mockMvc.perform(request).andExpect(status().is(204));
//    }
//
//    @Test
//    public void createAccountWithWrongData_BlankAccountName() throws Exception {
//        var request = post("/v2/users/{id}/accounts", 12)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"accountName\":\"\"}");
//
//        mockMvc.perform(request).andExpect(status().is(400));
//    }
//
//    @Test
//    public void testCreateAccountWithWrongData_NameWithLessThanThreeLetters() throws Exception {
//        var request = post("/v2/users/{id}/accounts", 12)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"accountName\":\"a\"}");
//
//        mockMvc.perform(request).andExpect(status().is(400));
//    }
//
//    @Test
//    public void testCreateUser_WithCorrectData() throws Exception {
//        var request = post("/v2/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"userId\":\"133\"}");
//
//        mockMvc.perform(request).andExpect(status().is(204));
//    }
//
//    @Test
//    public void testCreateUserWithWrongData_WithNegativeDigit() throws Exception {
//        var request = post("/v2/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"userId\":\"-2\"}");
//
//        mockMvc.perform(request).andExpect(status().is(400));
//    }
//
//    @Test
//    public void testCreateUserWithWrongData_WithBlankUserId() throws Exception {
//        var request = post("/v2/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"userId\":\"\"}");
//
//        mockMvc.perform(request).andExpect(status().is(400));
//    }
}
