package com.gpb.middle.repository;

import com.gpb.middle.dto.response.UserDTO;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
@Getter
public class UserRepositoryImpl implements UserRepository{

    private List<UserDTO> users = new CopyOnWriteArrayList<>();

    @Override
    public void add(UserDTO userDTO) {
        users.add(userDTO);
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return users.stream()
                .filter(user -> user.getUserId() == id)
                .findFirst();
    }
}
