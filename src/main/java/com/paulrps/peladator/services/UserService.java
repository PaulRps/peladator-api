package com.paulrps.peladator.services;

import com.paulrps.peladator.domain.entities.User;

import java.util.Optional;

public interface UserService {

    public Optional<User> findByName(String name);

    public User save(User u);

    public Optional<User> findById(Long userId);
}
