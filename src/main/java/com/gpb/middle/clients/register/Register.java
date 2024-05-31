package com.gpb.middle.clients.register;

import com.gpb.middle.clients.register.registerImplementation.RegisterHttp;
import com.gpb.middle.clients.register.registerImplementation.RegisterInMemory;
import com.gpb.middle.dto.request.CreateUserDTO;
import com.gpb.middle.exception.CreateUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class Register {

    private RegisterHttp registerHttp;

    private RegisterInMemory registerInMemory;

    public Register(){}

    @Autowired(required = false)
    public Register(RegisterInMemory registerInMemory) {
        this.registerInMemory = registerInMemory;
    }

    @Autowired(required = false)
    public Register(RegisterHttp registerHttp) {
        this.registerHttp = registerHttp;
    }

    public ResponseEntity<String> exec(CreateUserDTO createUserDTO) {

        if (Objects.nonNull(registerHttp)) {
            var resultOfRequest = registerHttp.runRequest(createUserDTO);
            if (resultOfRequest.getStatusCode().isSameCodeAs(HttpStatus.BAD_REQUEST)) {
                throw new CreateUserException(resultOfRequest.getBody());
            }
            return ResponseEntity
                    .ok()
                    .body("Вы были успешно зарегистрированы!\nВаш ClientId: " + createUserDTO.getUserId());
        }

        if (Objects.nonNull(registerInMemory)) {
            if (registerInMemory.checkUser(createUserDTO)) {
                return ResponseEntity.ok().body("Вы уже зарегистрированы!");
            }
            var resultOfAdd = registerInMemory.addUser(createUserDTO);
            return ResponseEntity
                    .ok()
                    .body("Вы были успешно зарегистрированы!\nВаш ClientId: " + resultOfAdd.getUserId());
        }

        return ResponseEntity
                .ok()
                .body("На данный момент регистрация не доступна!");
    }
}