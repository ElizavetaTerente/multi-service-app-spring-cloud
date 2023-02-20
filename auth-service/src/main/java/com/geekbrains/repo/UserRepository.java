package com.geekbrains.repo;

import com.geekbrains.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Boolean existsByUserName(String username);
    Optional<User> findByUserName(String userName);

}


