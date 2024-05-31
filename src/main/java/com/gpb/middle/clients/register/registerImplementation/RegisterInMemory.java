package com.gpb.middle.clients.register.registerImplementation;

import com.gpb.middle.dto.request.CreateUserDTO;
import com.gpb.middle.dto.response.UserDTO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ConditionalOnProperty(value="project.register.memory.enabled")
public class RegisterInMemory {

    private final Set<UserDTO> users;

    public RegisterInMemory() {
        this.users = ConcurrentHashMap.newKeySet();
    }

    public boolean checkUser(CreateUserDTO createUserDTO) {
        var newUser = new UserDTO(Long.toString(createUserDTO.getUserId()));
        return users.contains(newUser);
    }

    public UserDTO addUser(CreateUserDTO createUserDTO) {
        var newUser = new UserDTO(Long.toString(createUserDTO.getUserId()));
        users.add(newUser);
        return newUser;
    }
}
