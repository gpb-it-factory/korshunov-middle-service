package com.gpb.middle.repo;

import com.gpb.middle.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(Long id);
    Optional<User> findByUserName(String userName);
}
