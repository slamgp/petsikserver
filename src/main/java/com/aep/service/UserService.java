package com.aep.service;

import com.aep.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

public class UserService implements UserDetailsService {
    Set<User> usersDao;

    public UserService() {

    }

    public UserService(Set<User> usersDao) {
        this.usersDao = usersDao;
    }

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        for (User user : usersDao) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        throw  new UsernameNotFoundException("User with user name: " + name + ", not found");
    }
}