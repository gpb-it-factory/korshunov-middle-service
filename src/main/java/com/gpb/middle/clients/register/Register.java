package com.gpb.middle.clients.register;

import com.gpb.middle.dto.request.CreateUserDTO;
import org.springframework.stereotype.Service;

@Service
public class Register {

    private final RegisterImpl registerImpl;

    public Register(RegisterImpl registerInt) {
        this.registerImpl = registerInt;
    }

    public void exec(CreateUserDTO createUserDTO) {
        registerImpl.runRequest(createUserDTO);
    }
}