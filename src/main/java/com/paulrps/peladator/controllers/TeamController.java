package com.paulrps.peladator.controllers;

import com.paulrps.peladator.domain.dto.SortTeamDto;
import com.paulrps.peladator.domain.dto.TeamDto;
import com.paulrps.peladator.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "https://peladator.netlify.com"}, maxAge = 3600)
@RestController
@RequestMapping("team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping("sort-teams")
    List<TeamDto> sortTeams(@RequestBody SortTeamDto dto) {
        return teamService.sort(dto);
    }
}
