package com.gpb.middle.services.userRegisterService.userRegisterServiceClients;

import com.gpb.middle.dto.request.CreateUserDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.dto.response.UserDTO;
import com.gpb.middle.repository.UserRepository;
import com.gpb.middle.services.userRegisterService.UserRegisterServiceClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value="project.memory.enabled")
public class UserRegisterServiceInMemoryClient implements UserRegisterServiceClient {

    private final UserRepository userRepository;

    public UserRegisterServiceInMemoryClient(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkUser(UserDTO user) {
        return userRepository.getUsers().contains(user);
    }

    public ResponseEntity<Error> runRequest(CreateUserDTO createUserDTO) {
        var newUser = new UserDTO(createUserDTO.getUserId());
        if (checkUser(newUser)) {
            return ResponseEntity.status(400).body(new Error("Вы уже зарегистрированы!", "400"));
        }
        userRepository.getUsers().add(newUser);
        return ResponseEntity.status(204).build();
    }
}
