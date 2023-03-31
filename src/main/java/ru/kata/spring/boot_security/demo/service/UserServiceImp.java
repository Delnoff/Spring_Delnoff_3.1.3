package ru.kata.spring.boot_security.demo.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> read() {
        return userDao.read();
    }

    @Override
    @Transactional
    public void update(long id, User user) {
        userDao.update(id,user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User showId(long id) {
        return userDao.showId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User readUser(String username) {
        return userDao.readUser(username);
    }

}
