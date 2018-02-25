package com.aep.service;

import com.aep.model.exception.EmailIsBlockedException;
import com.aep.model.exception.EmailIsBuseException;
import com.aep.model.user.Role;
import com.aep.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    Set<User> usersDao;
    @Autowired
    PasswordEncoder passwordEncoder;

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
        throw new UsernameNotFoundException("User with user name: " + name + ", not found");
    }

    public UserDetails registrateUser(String email, String password) throws EmailIsBuseException,EmailIsBlockedException {
        UserDetails newUser = null;
        try {
            loadUserByUsername(email);
            throw new EmailIsBuseException();
        } catch (UsernameNotFoundException exception) {
            newUser = addNewUser(email, password);
        }

        return newUser;
    }

    protected UserDetails addNewUser(String email, String password) {
        Set<String> roles = new HashSet<String>();
        roles.add(Role.USER);
        User newUser = new User(3, email, passwordEncoder.encode(password), roles);
        usersDao.add(newUser);
        return newUser;
    }


}