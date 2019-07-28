package com.paulrps.peladator.services.impl;

import com.paulrps.peladator.domain.entities.User;
import com.paulrps.peladator.repositories.UserRepository;
import com.paulrps.peladator.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User save(User u) {

        if (!Optional.ofNullable(u).isPresent()){
            return null;
        }

        return userRepository.save(u);
    }
}
