package com.example.springBootCrud.dao;

import com.example.springBootCrud.model.Role;
import java.util.List;

public interface RoleDao {

    Role getRoleById(long id);

    List<Role> getAllRoles();

    void saveRole(Role role);
}
