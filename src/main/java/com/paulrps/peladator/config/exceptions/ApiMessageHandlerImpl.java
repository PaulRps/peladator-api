package com.paulrps.peladator.config.exceptions;

import com.paulrps.peladator.config.YamlPropertySourceFactory;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Setter
@PropertySource(value = "messages.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties
@Component
public class ApiMessageHandlerImpl implements ApiMessageHandler {

  private Map<String, String> error;
  private Map<String, String> warning;

  @Override
  public String getMessage(ApiException ex) {
    String message = getMessage(ex.getCustomMessage());
    if (message.contains(ApiMessageEnum.COMPLEMENT_TOKEN)) {
      return getMessage(ex.getCustomMessage(), ex.getComplement());
    }
    return message;
  }

  @Override
  public String getMessage(ApiMessageEnum errorEnum, String complement) {
    return getMessageMap(errorEnum.getCode())
        .get(errorEnum.getCode())
        .replace(ApiMessageEnum.COMPLEMENT_TOKEN, complement);
  }

  @Override
  public String getMessage(ApiMessageEnum errorEnum) {
    return getMessageMap(errorEnum.getCode()).get(errorEnum.getCode());
  }

  @Override
  public String getDetail(Throwable rootException) {
    return Optional.ofNullable(rootException)
        .map(ex -> String.format("%s: %s", ex.getClass().toString(), ex.getMessage()))
        .orElse("");
  }

  private Map<String, String> getMessageMap(String code) {
    if (code.startsWith("E")) {
      return error;
    } else if (code.startsWith("W")) {
      return warning;
    }
    return Collections.emptyMap();
  }
}
