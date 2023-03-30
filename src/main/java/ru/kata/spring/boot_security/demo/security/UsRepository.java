package ru.kata.spring.boot_security.demo.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Optional;

@Repository
public interface UsRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
