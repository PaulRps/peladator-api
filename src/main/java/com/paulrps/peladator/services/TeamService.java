package com.paulrps.peladator.services;

import com.paulrps.peladator.domain.dto.TeamsDto;
import com.paulrps.peladator.domain.entities.Player;

import java.util.List;

public interface TeamService {

    public TeamsDto sort(List<Player> players);
}
