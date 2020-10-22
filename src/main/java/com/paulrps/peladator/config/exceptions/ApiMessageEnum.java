package com.paulrps.peladator.config.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiMessageEnum {
  ERROR_PARAMETER_NOT_PRESENT("E001", ApiMessageTypeEnum.ERROR, HttpStatus.BAD_REQUEST),
  ERROR_RESOURCE_NOT_FOUND("E002", ApiMessageTypeEnum.ERROR, HttpStatus.BAD_REQUEST),
  ERROR_ON_SAVE_ENTITY("E003", ApiMessageTypeEnum.ERROR, HttpStatus.INTERNAL_SERVER_ERROR),
  ERROR_ON_DELETE_ENTITY("E004", ApiMessageTypeEnum.ERROR, HttpStatus.INTERNAL_SERVER_ERROR),
  ERROR_INTERNAL_SERVER("E005", ApiMessageTypeEnum.ERROR, HttpStatus.INTERNAL_SERVER_ERROR),
  ERROR_TOKEN_NOT_PRESENT("E006", ApiMessageTypeEnum.ERROR, HttpStatus.BAD_REQUEST),
  ERROR_TOKEN_INVALID("E007", ApiMessageTypeEnum.ERROR, HttpStatus.BAD_REQUEST),
  ERROR_AUTHENTICATION_USER_NOT_FOUND("E008", ApiMessageTypeEnum.ERROR, HttpStatus.BAD_REQUEST),
  ERROR_TOKEN_CREATION("E009", ApiMessageTypeEnum.ERROR, HttpStatus.BAD_REQUEST);

  private String code;
  private ApiMessageTypeEnum apiMessageType;
  private HttpStatus status;

  ApiMessageEnum(
      final String code, final ApiMessageTypeEnum apiMessageType, final HttpStatus status) {
    this.code = code;
    this.apiMessageType = apiMessageType;
    this.status = status;
  }

  public static final String COMPLEMENT_TOKEN = "{0}";
}
