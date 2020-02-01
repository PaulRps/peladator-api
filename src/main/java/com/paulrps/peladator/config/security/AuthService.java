package com.paulrps.peladator.config.security;

import com.paulrps.peladator.domain.entities.User;
import com.paulrps.peladator.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userService.findByName(s);

        if (user.isPresent()) {
            return user.get();
        }

        throw new UsernameNotFoundException(String.format("Invalid data - User: %s not found", s));
    }
}