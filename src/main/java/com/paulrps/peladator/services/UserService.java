package com.paulrps.peladator.services;

import com.paulrps.peladator.domain.dto.UserFormDto;
import com.paulrps.peladator.domain.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

  Optional<User> getOne(Long userId);

  UserFormDto getFormData();

  Optional<User> getOne(String name);

  List<User> getAll();

  User save(User u);

  boolean delete(Long id);
}
