package com.gpb.middle.clients.register.registerImplementation;

import com.gpb.middle.clients.register.RegisterImpl;
import com.gpb.middle.dto.request.CreateUserDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.dto.response.UserDTO;
import com.gpb.middle.exception.CreateUserException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ConditionalOnProperty(value="project.register.memory.enabled")
public class RegisterInMemory implements RegisterImpl {

    private final Set<UserDTO> users;

    public RegisterInMemory() {
        this.users = ConcurrentHashMap.newKeySet();
    }

    public void checkUser(UserDTO user) {
        if (users.contains(user)) {
            var error = new Error("Вы уже зарегистрированы!", "400");
            throw new CreateUserException(error);
        };
    }

    public void runRequest(CreateUserDTO createUserDTO) {
        var newUser = new UserDTO(Long.toString(createUserDTO.getUserId()));
        checkUser(newUser);
        users.add(newUser);
    }
}
