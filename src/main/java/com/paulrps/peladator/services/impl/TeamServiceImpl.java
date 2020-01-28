package com.paulrps.peladator.services.impl;

import com.paulrps.peladator.domain.dto.SortTeamDto;
import com.paulrps.peladator.domain.dto.TeamDto;
import com.paulrps.peladator.domain.enums.SortTeamStrategyEnum;
import com.paulrps.peladator.services.PlayerService;
import com.paulrps.peladator.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    PlayerService playerService;

    @Override
    public SortTeamDto loadTeamsPage() {
        return SortTeamDto.builder()
            .sortStrategies(Stream.of(SortTeamStrategyEnum.values())
                .filter(s -> !SortTeamStrategyEnum.SKILL_LEVEL.isEqual(s))
                .collect(toList()))
            .playersGroupedByPosition(playerService.groupByPositionAndSort())
            .build();
    }

    @Override
    public List<TeamDto> sort(SortTeamDto dto) {
        if (!Optional.ofNullable(dto).isPresent()) {
            //TODO: throw exception
            throw new RuntimeException("");
        }
        if (Optional.ofNullable(dto.getSortStrategy()).isPresent()) {
            return dto.getSortStrategy().sort(dto);
        } else {
            return SortTeamStrategyEnum.SKILL_LEVEL.sort(dto);
        }
    }
}
