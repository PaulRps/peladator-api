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

  @Autowired TeamService teamService;

  @PreAuthorize("permitAll()")
  @GetMapping("load-teams-page")
  ResponseEntity<SortTeamDto> loadTeamsPage() {
    return ResponseEntity.ok(teamService.loadTeamsPage());
  }

  @PreAuthorize("permitAll()")
  @PostMapping("sort-teams")
  ResponseEntity<List<TeamDto>> sortTeams(@RequestBody SortTeamDto dto) {
    return ResponseEntity.ok(teamService.sort(dto));
  }
}
