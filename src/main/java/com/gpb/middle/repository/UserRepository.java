package com.gpb.middle.repository;

import com.gpb.middle.dto.response.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<UserDTO> getUsers();

    void add(UserDTO userDTO);

    Optional<UserDTO> findById(Long id);
}
