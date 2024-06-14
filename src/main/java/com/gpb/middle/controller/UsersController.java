package com.gpb.middle.controller;

import com.gpb.middle.services.userRegisterService.UserRegisterService;
import com.gpb.middle.dto.request.CreateUserDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/users")
public class UsersController {

    private final UserRegisterService userRegisterService;

    public UsersController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        userRegisterService.exec(createUserDTO);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
