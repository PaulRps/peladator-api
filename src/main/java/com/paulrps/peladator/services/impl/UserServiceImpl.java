package com.paulrps.peladator.services.impl;

import static java.util.stream.Collectors.toList;

import com.paulrps.peladator.domain.dto.UserFormDto;
import com.paulrps.peladator.domain.entities.User;
import com.paulrps.peladator.domain.enums.RoleEnum;
import com.paulrps.peladator.repositories.UserRepository;
import com.paulrps.peladator.services.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired UserRepository userRepository;

  @Override
  public Optional<User> getOne(String name) {
    return userRepository.findByName(name);
  }

  @Override
  public Optional<User> getOne(Long userId) {
    return userRepository.findById(userId);
  }

  @Override
  public UserFormDto getFormData() {
    return UserFormDto.builder().roles(Stream.of(RoleEnum.values()).collect(toList())).build();
  }

  @Override
  public List<User> getAll() {
    return userRepository.findAll();
  }

  @Override
  public User save(User u) {

    if (!Optional.ofNullable(u).isPresent()) {
      return null;
    }

    if (u.getPassword().length() <= 8) {
      u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
    }

    return userRepository.save(u);
  }

  @Override
  public boolean delete(Long id) {
    if (!Optional.ofNullable(id).isPresent()) {
      throw new RuntimeException("");
    }

    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      userRepository.deleteById(id);
      return true;
    }
    return false;
  }
}
