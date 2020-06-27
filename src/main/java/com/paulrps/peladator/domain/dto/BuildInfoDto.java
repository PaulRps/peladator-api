package com.paulrps.peladator.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildInfoDto {

  private String group;

  private String artifact;

  private String version;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private Date dtBuild;

  private String developerGitHub;

  private String developerName;

  private String developerLinkedin;
}
