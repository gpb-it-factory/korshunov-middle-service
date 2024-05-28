package com.gpb.middle.commander;

import com.gpb.middle.dto.request.CreateUserDTO;
import com.gpb.middle.dto.response.UserDTO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Component
public class Register {

    private final RestTemplate restTemplate;

    private ArrayList<UserDTO> userDTOArrayList;

    public Register(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.userDTOArrayList = new ArrayList<>();
    }

    //    public UserDTO exec(CreateUserDTO createUserDTO) {
//        HttpEntity<CreateUserDTO> entity = new HttpEntity<>(createUserDTO);
//        ResponseEntity<UserDTO> response = restTemplate.postForEntity(
//                "http://localhost:8082:/register",
//                entity,
//                UserDTO.class);
//        return response.getBody();
//    }

    public String exec(CreateUserDTO createUserDTO) {
        var inArray = userDTOArrayList.stream()
                .filter(u -> u.getUserId().equals(Long.toString(createUserDTO.getUserId())))
                .findFirst();
        if (inArray.isPresent()) {
            return "Вы уже зарегистрированы!\nВаш UserId: " + inArray.get().getUserId();
        }

        userDTOArrayList.add(new UserDTO(Long.toString(createUserDTO.getUserId())));

        var result = userDTOArrayList.stream()
                .filter(u -> u.getUserId().equals(Long.toString(createUserDTO.getUserId())))
                .findFirst();
        if (result.isPresent()) {
            return "Вы успешно зарегистрированы!\nВаш UserId: " + createUserDTO.getUserId();
        }
        return "Произошла ошибка во время регистрации!";
    }
}
