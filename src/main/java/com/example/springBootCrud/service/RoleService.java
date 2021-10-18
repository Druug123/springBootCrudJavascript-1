package com.example.springBootCrud.service;

import com.example.springBootCrud.model.Role;
import java.util.List;

public interface RoleService {

    Role getRoleById(long id);

    List<Role> getAllRoles();

    void saveRole(Role role);
}
