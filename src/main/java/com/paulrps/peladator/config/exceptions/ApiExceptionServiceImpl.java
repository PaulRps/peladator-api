package com.paulrps.peladator.config.exceptions;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

@Service
public class ApiExceptionServiceImpl implements ApiExceptionService {
  private static ApiMessageHandler apiMessageHandler;

  @Autowired
  ApiExceptionServiceImpl(final ApiMessageHandler apiMessageHandler) {
    ApiExceptionServiceImpl.apiMessageHandler = apiMessageHandler;
  }

  @Override
  public ResponseEntity<ApiErrorResponse> handleDefaultException(
      Exception ex, ServletWebRequest request) {
    return new ResponseEntity<>(
        ApiErrorResponse.builder()
            .timestamp(new Date())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(apiMessageHandler.getMessage(ApiMessageEnum.ERROR_INTERNAL_SERVER, "api"))
            .detail(apiMessageHandler.getDetail(getRootCause(ex)))
            .uri(request.getRequest().getRequestURI())
            .receivedHttpHeaders(getHeaders(request.getRequest()))
            .build(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<ApiErrorResponse> handlePeladatorExceptino(
      ApiException ex, ServletWebRequest request) {
    return new ResponseEntity<>(
        ApiErrorResponse.builder()
            .timestamp(new Date())
            .status(ex.getCustomMessage().getStatus().value())
            .message(apiMessageHandler.getMessage(ex))
            .messageType(ex.getCustomMessage().getApiMessageType())
            .detail(apiMessageHandler.getDetail(getRootCause(ex.getRealException())))
            .uri(request.getRequest().getRequestURI())
            .receivedHttpHeaders(getHeaders(request.getRequest()))
            .build(),
        ex.getCustomMessage().getStatus());
  }

  @Override
  public ResponseEntity<ApiErrorResponse> handlePeladatorExceptino(
      ApiException ex, HttpServletRequest request) {
    return new ResponseEntity<>(
        ApiErrorResponse.builder()
            .timestamp(new Date())
            .status(ex.getCustomMessage().getStatus().value())
            .message(apiMessageHandler.getMessage(ex))
            .messageType(ex.getCustomMessage().getApiMessageType())
            .detail(apiMessageHandler.getDetail(getRootCause(ex.getRealException())))
            .uri(request.getRequestURI())
            .receivedHttpHeaders(getHeaders(request))
            .build(),
        ex.getCustomMessage().getStatus());
  }

  @Override
  public ResponseEntity<ApiErrorResponse> handleDefaultException(
      ResponseEntity<Object> objectResponseEntity, Throwable ex, ServletWebRequest request) {

    if (ApiException.class.isInstance(ex)) {
      ApiException apiException = (ApiException) ex;
      return new ResponseEntity<>(
          ApiErrorResponse.builder()
              .timestamp(new Date())
              .status(apiException.getCustomMessage().getStatus().value())
              .uri(request.getRequest().getRequestURI())
              .message(apiMessageHandler.getMessage(apiException))
              .messageType(apiException.getCustomMessage().getApiMessageType())
              .detail(apiMessageHandler.getDetail(getRootCause(ex)))
              .receivedHttpHeaders(getHeaders(request.getRequest()))
              .build(),
          objectResponseEntity.getHeaders(),
          apiException.getCustomMessage().getStatus());
    } else {
      return new ResponseEntity<>(
          ApiErrorResponse.builder()
              .timestamp(new Date())
              .status(objectResponseEntity.getStatusCode().value())
              .uri(request.getRequest().getRequestURI())
              .message(apiMessageHandler.getMessage(ApiMessageEnum.ERROR_INTERNAL_SERVER, "api"))
              .messageType(ApiMessageEnum.ERROR_INTERNAL_SERVER.getApiMessageType())
              .detail(apiMessageHandler.getDetail(getRootCause(ex)))
              .receivedHttpHeaders(getHeaders(request.getRequest()))
              .build(),
          objectResponseEntity.getHeaders(),
          objectResponseEntity.getStatusCode());
    }
  }

  private Throwable getRootCause(Throwable ex) {
    return Optional.ofNullable(ex)
        .map(
            e -> {
              Throwable rootCause = e;
              while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
                rootCause = rootCause.getCause();
              }
              return rootCause;
            })
        .orElse(ex);
  }

  private HttpHeaders getHeaders(HttpServletRequest request) {
    return Collections.list(request.getHeaderNames()).stream()
        .collect(
            Collectors.toMap(
                Function.identity(),
                h -> Collections.list(request.getHeaders(h)),
                (oldValue, newValue) -> newValue,
                HttpHeaders::new));
  }
}
