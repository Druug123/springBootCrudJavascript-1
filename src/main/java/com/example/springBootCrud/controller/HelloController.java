package com.example.springBootCrud.controller;


import com.example.springBootCrud.model.Role;
import com.example.springBootCrud.model.User;
import com.example.springBootCrud.service.RoleService;
import com.example.springBootCrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HelloController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public HelloController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String welcome() {
        return "index";
    }

    @GetMapping("/registration")
    public String newUser(@ModelAttribute("user") User user) {
        return "/registration";
    }

    @PostMapping("/registration")
    public String create(@ModelAttribute("user") User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleById(2));
        user.setRoles(roles);
        userService.create(user);
        return "redirect:/login";
    }
}
