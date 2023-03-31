package ru.kata.spring.boot_security.demo.dao;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    public UserDaoImp(EntityManager entityManager, PasswordEncoder passwordEncoder) {
        this.entityManager = entityManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public List<User> read() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public User readUser(String username) {
        return (User) entityManager.createQuery("select u from User u where u.username = ?1")
                .setParameter(1, username).getSingleResult();
    }

    @Override
    public void update(long id, User user) {
        User us = showId(id);
        us.setUsername(user.getUsername());
        us.setSurname(user.getSurname());
        us.setAge(user.getAge());
        us.setPassword(user.getPassword());
        us.setRole(user.getRole());
        entityManager.merge(us);
    }

    @Override
    public void delete(long id) {
        User user = showId(id);
        entityManager.remove(user);
    }

    @Override
    public User showId(long id) {
        return entityManager.find(User.class, id);
    }

}
