package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RSTController {
    private final UserService userService;

    @Autowired
    public RSTController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public List<User> getUsers(){
        return userService.getUserList();
    }


    @GetMapping("/getUser")
    public User user(@AuthenticationPrincipal User user) {
        return userService.getUserById(user.getId());
    }





}
