package com.example.springBootCrud.controller;

import com.example.springBootCrud.model.Role;
import com.example.springBootCrud.model.User;
import com.example.springBootCrud.service.RoleService;
import com.example.springBootCrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String index(Model model, Principal principal) {
        model.addAttribute("users", userService.index());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user",
                userService.getUserByName(principal.getName()));
        return "admin/index";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user, @RequestParam(value = "roleList", required = false) long[] roleList) {
        Set<Role> roles = new HashSet<>();
        if (roleList != null) {
            for (long roleId : roleList) {
                roles.add(roleService.getRoleById(roleId));
            }
        }
        user.setRoles(roles);
        userService.create(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam(value = "roleList", required = false) long[] roleList) {
        Set<Role> roles = new HashSet<>();
        if (roleList != null) {
            for (long roleId : roleList) {
                roles.add(roleService.getRoleById(roleId));
            }
        }
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
