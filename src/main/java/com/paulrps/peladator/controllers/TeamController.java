package com.paulrps.peladator.controllers;

import com.paulrps.peladator.domain.dto.SortTeamDto;
import com.paulrps.peladator.domain.dto.TeamDto;
import com.paulrps.peladator.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "https://peladator.netlify.com"}, maxAge = 3600)
@RestController
@RequestMapping("team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("load-teams-page")
    ResponseEntity<SortTeamDto> loadTeamsPage() {
        return ResponseEntity.ok(teamService.loadTeamsPage());
    }

    @PostMapping("sort-teams")
    ResponseEntity<List<TeamDto>> sortTeams(@RequestBody SortTeamDto dto) {
        return ResponseEntity.ok(teamService.sort(dto));
    }
}
