package com.gpb.middle.controller;

import com.gpb.middle.commander.Register;
import com.gpb.middle.dto.request.CreateAccountDTO;
import com.gpb.middle.dto.request.CreateUserDTO;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/users")
public class UsersController {

    @Autowired
    private Register register;

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public String createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        return register.exec(createUserDTO);
    }

//    @GetMapping(path = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void show(@PathParam("id") Long id) {
//
//    }
//
//    @PostMapping(path = "/{id}/accounts")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void createAccount(@PathParam("id") Long id, @RequestBody CreateAccountDTO createAccountDTO) {
//
//    }
//
//    @GetMapping(path = "/{id}/accounts")
//    @ResponseStatus(HttpStatus.OK)
//    public  showAccounts(@PathParam("id") Long id) {
//
//    }
}
