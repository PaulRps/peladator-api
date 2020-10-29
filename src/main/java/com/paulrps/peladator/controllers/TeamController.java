package com.paulrps.peladator.controllers;

import com.paulrps.peladator.domain.dto.SortTeamDto;
import com.paulrps.peladator.domain.dto.TeamDto;
import com.paulrps.peladator.services.TeamService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(
    origins = {"http://localhost:4200", "https://peladator.netlify.com"},
    maxAge = 3600)
@RestController
@RequestMapping("team")
public class TeamController {
  private static TeamService teamService;

  @Autowired
  TeamController(TeamService teamService) {
    TeamController.teamService = teamService;
  }

  @PreAuthorize("permitAll()")
  @GetMapping("load-page")
  ResponseEntity<SortTeamDto> loadPage() {
    return ResponseEntity.ok(teamService.loadPage());
  }

  @PreAuthorize("permitAll()")
  @PostMapping("sort")
  ResponseEntity<List<TeamDto>> sortTeams(@RequestBody SortTeamDto dto) {
    return ResponseEntity.ok(teamService.sort(dto));
  }
}
