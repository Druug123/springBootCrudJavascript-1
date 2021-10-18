package com.example.springBootCrud.service;

import com.example.springBootCrud.dao.UserDao;
import com.example.springBootCrud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User myUser = userDao.getUserByName(name);
        if (myUser == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", name));
        }
        return myUser;
    }
}
