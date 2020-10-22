package com.paulrps.peladator.services.impl;

import com.paulrps.peladator.config.exceptions.ApiException;
import com.paulrps.peladator.config.exceptions.ApiMessageEnum;
import com.paulrps.peladator.services.PasswordEncrypter;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncrypterImpl implements PasswordEncrypter {

  private static PasswordEncoder passwordEncoder;

  @Autowired
  PasswordEncrypterImpl(final PasswordEncoder passwordEncoder) {
    PasswordEncrypterImpl.passwordEncoder = passwordEncoder;
  }

  @Override
  public String encrypt(String password) {
    return Optional.ofNullable(password)
        .map(pwd -> passwordEncoder.encode(pwd))
        .orElseThrow(
            () -> new ApiException(ApiMessageEnum.ERROR_PARAMETER_NOT_PRESENT, "password"));
  }
}
