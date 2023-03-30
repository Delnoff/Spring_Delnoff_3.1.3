package ru.kata.spring.boot_security.demo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "People")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @NotEmpty(message = "The name fields should not be empty")
    @Size(min = 2,max = 50, message = "The name must be between 2 and 50 characters long")
    @Column(name = "username",nullable = false)
    private String username;

    @NotEmpty(message = "Surname fields should not be empty")
    @Size(min = 2,max = 50, message = "The surname must be between 2 and 50 characters long")
    @Column(name = "user_surname",nullable = false)
    private String surname;

    @NotEmpty(message = "Password fields should not be empty")
    @Column(name = "user_password",nullable = false)
    private String password;

    @Min(value = 1,message = "The age cannot be less than or equal to 0")
    @Max(value = 99,message = "The age cannot be more than 100")
    @Column(name = "user_age")
    private int age;

    @Column(name = "role")
    private String role;

    public User() {
    }

    public User(String username, String surname, String password, int age, String role) {
        this.username = username;
        this.surname = surname;
        this.password = password;
        this.age = age;
        this.role = role;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", surname='" + surname + '\'' +
               ", password='" + password + '\'' +
               ", age=" + age +
               ", role='" + role + '\'' +
               '}';
    }
}
