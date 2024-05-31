package com.gpb.middle.controller;

import com.gpb.middle.commander.Register;
import com.gpb.middle.dto.request.CreateUserDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.dto.response.UserDTO;
import com.gpb.middle.exception.ErrorException;
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
        var result = register.exec(createUserDTO);
        if (result instanceof UserDTO userDTO) {
            return ResponseEntity.ok()
                    .body("Вы успешно зарегистрированы!\nВаш UserId: " + userDTO.getUserId());
        }
        throw new ErrorException((Error) result);
    }
}
