package com.paulrps.peladator.services;

import com.paulrps.peladator.domain.dto.UserFormDto;
import com.paulrps.peladator.domain.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> getOne(Long userId);

    public UserFormDto getFormData();

    public Optional<User> getOne(String name);

    public List<User> getAll();

    public User save(User u);

    public boolean delete(Long id);
}
