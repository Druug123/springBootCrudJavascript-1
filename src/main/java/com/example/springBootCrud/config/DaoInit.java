package com.example.springBootCrud.config;

import com.example.springBootCrud.model.Role;
import com.example.springBootCrud.model.User;
import com.example.springBootCrud.service.RoleService;
import com.example.springBootCrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DaoInit {

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostConstruct
    private void createUsers(){
        roleService.saveRole(new Role("ADMIN"));
        roleService.saveRole(new Role("USER"));
        Set<Role> rolesAdmin = new HashSet<>(roleService.getAllRoles());
        userService.create(new User("admin", "admin", 99, "admin@admin.com", rolesAdmin));
        Set<Role> rolesUser = new HashSet<>();
        rolesUser.add(roleService.getRoleById(2));
        userService.create(new User("user", "user", 99, "user@user.com", rolesUser));
    }
}
