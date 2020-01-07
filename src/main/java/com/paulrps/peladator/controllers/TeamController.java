package com.paulrps.peladator.controllers;

import com.paulrps.peladator.domain.dto.TeamsDto;
import com.paulrps.peladator.domain.entities.Player;
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
    TeamsDto sortTeams(@RequestBody List<Player> players) {
        return teamService.sort(players);
    }
}
