package com.paulrps.peladator.controllers;

import com.paulrps.peladator.domain.dto.BuildInfoDto;
import com.paulrps.peladator.services.APIInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("info")
public class APIInfoController {

  @Autowired private APIInfoService apiInfoService;

  @GetMapping("build")
  public ResponseEntity<BuildInfoDto> getBuildInfo() {

    return ResponseEntity.ok(apiInfoService.getBuildInfo());
  }
}
