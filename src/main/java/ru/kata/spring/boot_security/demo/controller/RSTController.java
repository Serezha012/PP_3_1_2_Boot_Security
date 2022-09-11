package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
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


    @PostMapping("/createUser")
    public  User createUser(@RequestBody User user){
        System.out.println(user);
        userService.save(user);
        return user;
    }


    @DeleteMapping("/user/{id}")
    public List<User> delete(@PathVariable("id") int id) {
        userService.delete(id);
        return userService.getUserList();
    }


    @PatchMapping("/{id}")
    public List<User> editUser(@RequestBody User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return userService.getUserList();
    }


}
