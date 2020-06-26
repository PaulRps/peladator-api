package com.paulrps.peladator.services.impl;

import com.paulrps.peladator.domain.dto.BuildInfoDto;
import com.paulrps.peladator.services.APIInfoService;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

@Service
public class APIInfoServiceImpl implements APIInfoService {

  @Autowired private BuildProperties buildProperties;

  @Override
  public BuildInfoDto getBuildInfo() {

    return BuildInfoDto.builder()
        .group(buildProperties.getGroup())
        .artifact(buildProperties.getArtifact())
        .version(buildProperties.getVersion())
        .dtBuild(Date.from(buildProperties.getTime()))
        .developerUrl(buildProperties.get("developer.url"))
        .developerName(buildProperties.get("developer.name"))
        .build();
  }
}
