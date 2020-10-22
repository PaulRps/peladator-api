package com.paulrps.peladator.config.security;

import com.paulrps.peladator.config.YamlPropertySourceFactory;
import java.util.List;
import java.util.Map;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Setter
@Component
@PropertySource(value = "spring-securited-paths.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties
public class SpringSecuritedPaths {

  private Map<String, List<String>> permited;

  public String[] getPermitedPathsByMethod(HttpMethod method) {
    return permited.get(method.name()).stream().toArray(String[]::new);
  }

  public String[] getAllPermitedPaths() {
    return permited.get("all").stream().toArray(String[]::new);
  }
}
