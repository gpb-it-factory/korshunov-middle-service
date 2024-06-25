package com.gpb.middle.services.userRegisterService.userRegisterServiceClients;

import com.gpb.middle.dto.request.CreateUserDTO;
import com.gpb.middle.dto.response.Error;
import com.gpb.middle.models.User;
import com.gpb.middle.repo.UserRepository;
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

    public boolean checkUser(Long id) {
        return userRepository.findByUserId(id).isPresent();
    }

    public ResponseEntity<Error> runRequest(CreateUserDTO createUserDTO) {
        if (checkUser(createUserDTO.getUserId())) {
            return ResponseEntity.status(400).body(new Error("Вы уже зарегистрированы!",
                    "type",
                    "400",
                    "trace_id"));
        }
        var newUser = new User();
        newUser.setUserId(createUserDTO.getUserId());
        newUser.setUserName(createUserDTO.getUserName());
        userRepository.save(newUser);
        return ResponseEntity.status(204).build();
    }
}
