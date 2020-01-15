package com.paulrps.peladator.services;

import com.paulrps.peladator.domain.dto.SortTeamDto;
import com.paulrps.peladator.domain.dto.TeamDto;

import java.util.List;

public interface TeamService {

    public SortTeamDto loadTeamsPage();
    public List<TeamDto> sort(SortTeamDto dto);
}
