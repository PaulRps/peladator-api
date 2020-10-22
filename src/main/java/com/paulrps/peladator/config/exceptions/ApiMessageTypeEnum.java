package com.paulrps.peladator.config.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum ApiMessageTypeEnum {
  ERROR(1, "Error"),
  WARNING(2, "Warning");
  private Integer id;
  private String description;
}
