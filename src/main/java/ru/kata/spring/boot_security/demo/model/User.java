package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "users")

public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;


    @Column(name = "name")
    private String name;

    @Column(name = "surname")

    private String surname;

    @Column(name = "age")
    private int age;

    public User() {
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
}
