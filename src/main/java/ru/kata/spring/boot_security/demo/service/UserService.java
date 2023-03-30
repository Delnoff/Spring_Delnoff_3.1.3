package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void create(User user);
    List<User> read();
    void update(long id, User user);
    void delete(long id);
    User showId(long id);
    User readUser(String username);
}
