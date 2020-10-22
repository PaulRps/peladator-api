package com.paulrps.peladator.config.exceptions;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

  private final ApiMessageEnum customMessage;
  private final Throwable realException;
  private final String complement;

  public ApiException(
      final ApiMessageEnum customMessage, final Throwable realException, final String complement) {
    super(realException.getMessage());
    this.customMessage = customMessage;
    this.realException = realException;
    this.complement = complement;
  }

  public ApiException(final ApiMessageEnum customMessage, final Throwable realException) {
    super(customMessage.getCode());
    this.customMessage = customMessage;
    this.realException = realException;
    this.complement = null;
  }

  public ApiException(final ApiMessageEnum customMessage, final String complement) {
    super(complement);
    this.customMessage = customMessage;
    this.realException = null;
    this.complement = complement;
  }

  public ApiException(final ApiMessageEnum customMessage) {
    super();
    this.customMessage = customMessage;
    this.realException = null;
    this.complement = null;
  }
}
