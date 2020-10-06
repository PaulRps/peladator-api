package com.paulrps.peladator.services;

import com.paulrps.peladator.domain.dto.SortTeamDto;
import com.paulrps.peladator.domain.dto.TeamDto;
import java.util.List;

public interface TeamService {

  SortTeamDto loadPage();

  List<TeamDto> sort(SortTeamDto dto);
}
