package com.paulrps.peladator.services.impl;

import static java.util.stream.Collectors.toList;

import com.paulrps.peladator.config.exceptions.ApiException;
import com.paulrps.peladator.config.exceptions.ApiMessageEnum;
import com.paulrps.peladator.domain.dto.UserFormDto;
import com.paulrps.peladator.domain.entities.User;
import com.paulrps.peladator.domain.enums.RoleEnum;
import com.paulrps.peladator.repositories.UserRepository;
import com.paulrps.peladator.services.PasswordEncrypter;
import com.paulrps.peladator.services.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  private static UserRepository userRepository;
  private static PasswordEncrypter passwordEncrypter;

  @Autowired
  UserServiceImpl(final UserRepository userRepository, final PasswordEncrypter passwordEncrypter) {
    UserServiceImpl.userRepository = userRepository;
    UserServiceImpl.passwordEncrypter = passwordEncrypter;
  }

  @Override
  public Optional<User> find(String name) {
    Optional.ofNullable(name)
        .orElseThrow(
            () -> {
              log.error("Parameter USER_NAME is null");
              return new ApiException(ApiMessageEnum.ERROR_PARAMETER_NOT_PRESENT, "USER_NAME");
            });

    try {
      log.debug("querying user[name={}]", name);
      return userRepository.findByName(name);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ApiException(
          ApiMessageEnum.ERROR_RESOURCE_NOT_FOUND, e, String.format("User[name=%s]", name));
    }
  }

  @Override
  public Optional<User> find(Long userId) {
    Optional.ofNullable(userId)
        .orElseThrow(
            () -> {
              log.error("Parameter USER_ID is null");
              return new ApiException(ApiMessageEnum.ERROR_PARAMETER_NOT_PRESENT, "USER_ID");
            });
    try {
      log.debug("querying user[id={}]", userId);
      return userRepository.findById(userId);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ApiException(
          ApiMessageEnum.ERROR_RESOURCE_NOT_FOUND, e, String.format("User[id=%d]", userId));
    }
  }

  @Override
  public UserFormDto getFormData() {
    try {
      return UserFormDto.builder().roles(Stream.of(RoleEnum.values()).collect(toList())).build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ApiException(ApiMessageEnum.ERROR_INTERNAL_SERVER, e, "get user form data");
    }
  }

  @Override
  public List<User> findAll() {
    try {
      log.debug("querying all users");
      return userRepository.findAll();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ApiException(ApiMessageEnum.ERROR_INTERNAL_SERVER, e, "all users");
    }
  }

  @Override
  public User save(User u) {

    Optional.ofNullable(u)
        .orElseThrow(
            () -> {
              log.error("Parameter user is null");
              return new ApiException(ApiMessageEnum.ERROR_PARAMETER_NOT_PRESENT, "user");
            });

    u.setPassword(passwordEncrypter.encrypt(u.getPassword()));

    try {

      log.debug("creating user {}}", u.toString());
      return userRepository.save(u);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      log.error(u.toString());
      throw new ApiException(ApiMessageEnum.ERROR_ON_SAVE_ENTITY, e, u.toString());
    }
  }

  @Override
  public void update(User u) {

    Optional.ofNullable(u)
        .orElseThrow(
            () -> {
              log.error("Parameter user is null");
              return new ApiException(ApiMessageEnum.ERROR_PARAMETER_NOT_PRESENT, "user");
            });

    Optional.ofNullable(u.getId())
        .orElseThrow(
            () -> {
              log.error("USER_ID parameter is null");
              return new ApiException(ApiMessageEnum.ERROR_PARAMETER_NOT_PRESENT, "USER_ID");
            });

    try {

      log.debug("updating user {}", u.toString());

      userRepository.save(u);

      log.debug("updated user");

    } catch (Exception e) {
      log.error(e.getMessage(), e);
      log.error(u.toString());
      throw new ApiException(ApiMessageEnum.ERROR_ON_SAVE_ENTITY, e, u.toString());
    }
  }

  @Override
  public void delete(Long id) {

    Optional.ofNullable(id)
        .orElseThrow(
            () -> {
              log.error("USER_ID parameter is null");
              return new ApiException(ApiMessageEnum.ERROR_PARAMETER_NOT_PRESENT, "USER_ID");
            });

    if (!userRepository.existsById(id)) {
      log.error("User[id={}] do not exist", id);
      throw new ApiException(
          ApiMessageEnum.ERROR_RESOURCE_NOT_FOUND, String.format("User[id=%d]", id));
    }

    try {

      log.debug("deleting user [id={}]", id);
      userRepository.deleteById(id);
      log.debug("deleted user");
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ApiException(
          ApiMessageEnum.ERROR_ON_DELETE_ENTITY, e, String.format("User[id=%d]", id));
    }
  }
}
