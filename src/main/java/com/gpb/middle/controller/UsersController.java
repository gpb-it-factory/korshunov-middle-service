package com.gpb.middle.controller;

import com.gpb.middle.dto.request.CreateAccountDTO;
import com.gpb.middle.dto.response.UserDTO;
import com.gpb.middle.services.createAccountService.CreateAccountService;
import com.gpb.middle.services.userRegisterService.UserRegisterService;
import com.gpb.middle.dto.request.CreateUserDTO;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/v2/users")
public class UsersController {

    private final UserRegisterService userRegisterService;

    private final CreateAccountService createAccountService;

    public UsersController(UserRegisterService userRegisterService, CreateAccountService createAccountService) {
        this.userRegisterService = userRegisterService;
        this.createAccountService = createAccountService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        userRegisterService.exec(createUserDTO);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/{id}/accounts")
    public ResponseEntity<?> createAccount(@PathVariable Long id,
                                           @Valid @RequestBody CreateAccountDTO createAccountDTO) {
        createAccountService.exec(id, createAccountDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
