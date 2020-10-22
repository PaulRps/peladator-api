package com.paulrps.peladator.config.exceptions;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

public interface ApiExceptionService {
  ResponseEntity<ApiErrorResponse> handleDefaultException(Exception ex, ServletWebRequest request);

  ResponseEntity<ApiErrorResponse> handlePeladatorExceptino(
      ApiException ex, ServletWebRequest request);

  ResponseEntity<ApiErrorResponse> handlePeladatorExceptino(
      ApiException ex, HttpServletRequest request);

  ResponseEntity<ApiErrorResponse> handleDefaultException(
      ResponseEntity<Object> objectResponseEntity, Throwable ex, ServletWebRequest request);
}
