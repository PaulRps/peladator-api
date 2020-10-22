package com.paulrps.peladator.config.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "dd-MM-YYYY HH:mm:ss",
      locale = "pt-BR",
      timezone = "Brazil/East")
  private Date timestamp;

  private Integer status;
  private ApiMessageTypeEnum messageType;
  private String message;
  private String detail;
  private String uri;
  private HttpHeaders receivedHttpHeaders;
}
