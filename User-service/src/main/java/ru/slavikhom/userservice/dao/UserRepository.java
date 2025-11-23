package ru.slavikhom.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.slavikhom.userservice.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByHandle(String handle);
    Boolean existsByHandle(String handle);
    Boolean existsByEmail(String email);
}
