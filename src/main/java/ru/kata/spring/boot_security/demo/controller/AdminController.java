package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.UserDetailsImpl;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getUserList(Model model) {
        model.addAttribute("getUserList", userService.getUserList());
        model.addAttribute("newUser", new User());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        int id = userDetails.getUser().getId();
        model.addAttribute("getUserById", userService.getUserById(id));
        model.addAttribute("rolesList", roleService.findAll());

        return "admin/users";
    }


    @PostMapping()
    public String createUser(@ModelAttribute("newUser") User user, @RequestParam("roles") long roles) {
        user.setRoles(roleService.getRoleByID(roles));
        userService.save(user);
        return "redirect:/admin";
    }


    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user, @PathVariable("id") int id, @RequestParam("roles") long roles) {
        user.setRoles(roleService.getRoleByID(roles));
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }


}
