package com.gpb.middle.controller;

import com.gpb.middle.clients.register.Register;
import com.gpb.middle.dto.request.CreateUserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/users")
public class UsersController {

    private final Register register;

    public UsersController(Register register) {
        this.register = register;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        return register.exec(createUserDTO);
    }
}
