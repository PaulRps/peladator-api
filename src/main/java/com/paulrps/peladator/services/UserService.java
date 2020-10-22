package com.paulrps.peladator.services;

import com.paulrps.peladator.domain.dto.UserFormDto;
import com.paulrps.peladator.domain.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

  Optional<User> find(Long userId);

  UserFormDto getFormData();

  Optional<User> find(String name);

  List<User> findAll();

  User save(User u);

  void update(User u);

  void delete(Long id);
}
