package com.paulrps.peladator.config.exceptions;

public interface ApiMessageHandler {
  String getMessage(ApiException ex);

  String getMessage(ApiMessageEnum errorEnum, String complement);

  String getMessage(ApiMessageEnum errorEnum);

  String getDetail(Throwable rootException);
}
