package com.paulrps.peladator.services;

import com.paulrps.peladator.domain.dto.UserFormDto;
import com.paulrps.peladator.domain.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> findById(Long userId);

    public UserFormDto getFormData();

    public Optional<User> findByName(String name);

    public List<User> findAll();

    public User save(User u);

    public boolean delete(Long id);
}
