package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "users")

public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;


    @Column(name = "username")
    private String username;


    @Column(name = "password")
    private String password;


    @Column(name = "name")
    private String name;

    @Column(name = "surname")

    private String surname;

    @Column(name = "age")
    private int age;


    @ManyToMany(fetch = FetchType.EAGER)

    private List<Role> roles;

    public User() {
    }

    public List<Role> getRoles() {
        return roles;
    }


    public String getRolesString() {
        StringJoiner joiner = new StringJoiner(",");
        for (Role role : roles) {
            joiner.add(role.toString().substring(5));
        }
        return joiner.toString();
    }

    public void setRoles(Role role) {
        this.roles = Collections.singletonList(role);
    }

    public User(String Name, String surname, int old) {
        this.name = Name;
        this.surname = surname;
        this.age = old;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int old) {
        this.age = old;
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

}
