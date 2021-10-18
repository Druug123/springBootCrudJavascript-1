package com.example.springBootCrud.dao;

import com.example.springBootCrud.model.User;
import java.util.List;

public interface UserDao {
    User getUserByName(String name);

    List<User> index();

    void create(User user);

    User show(long id);

    void update(User user);

    void delete(long id);
}
