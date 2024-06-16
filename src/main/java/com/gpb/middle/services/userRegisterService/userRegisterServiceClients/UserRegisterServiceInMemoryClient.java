package com.gpb.middle.services.userRegisterService.userRegisterServiceClients;

import com.gpb.middle.services.userRegisterService.UserRegisterServiceClient;
import com.gpb.middle.dto.request.CreateUserDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.dto.response.UserDTO;
import com.gpb.middle.exception.CreateUserException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ConditionalOnProperty(value="project.memory.enabled")
public class UserRegisterServiceInMemoryClient implements UserRegisterServiceClient {

    private final Set<UserDTO> users;

    public UserRegisterServiceInMemoryClient() {
        this.users = ConcurrentHashMap.newKeySet();
    }

    public boolean checkUser(UserDTO user) {
        return users.contains(user);
    }

    public ResponseEntity<Error> runRequest(CreateUserDTO createUserDTO) {
        var newUser = new UserDTO(Long.toString(createUserDTO.getUserId()));
        if (checkUser(newUser)) {
            return ResponseEntity.status(400).body(new Error("Вы уже зарегистрированы!", "400"));
        }
        users.add(newUser);
        return ResponseEntity.status(204).build();
    }
}
