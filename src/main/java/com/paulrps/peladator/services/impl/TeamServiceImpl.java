package com.paulrps.peladator.services.impl;

import static java.util.stream.Collectors.toList;

import com.paulrps.peladator.config.exceptions.ApiException;
import com.paulrps.peladator.config.exceptions.ApiMessageEnum;
import com.paulrps.peladator.domain.dto.SortTeamDto;
import com.paulrps.peladator.domain.dto.TeamDto;
import com.paulrps.peladator.domain.enums.SortTeamStrategyEnum;
import com.paulrps.peladator.services.PlayerService;
import com.paulrps.peladator.services.TeamService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TeamServiceImpl implements TeamService {

  @Autowired PlayerService playerService;

  @Override
  public SortTeamDto loadPage() {

    try {
      return SortTeamDto.builder()
          .sortStrategies(
              Stream.of(SortTeamStrategyEnum.values())
                  .filter(s -> !SortTeamStrategyEnum.SKILL_LEVEL.isEqual(s))
                  .collect(toList()))
          .playersGroupedByPosition(playerService.groupByPositionAndSort())
          .build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ApiException(ApiMessageEnum.ERROR_INTERNAL_SERVER, e, "loadPage");
    }
  }

  @Override
  public List<TeamDto> sort(SortTeamDto dto) {
    Optional.ofNullable(dto)
        .orElseThrow(
            () -> {
              log.error("Parameter dto is null");
              return new ApiException(
                  ApiMessageEnum.ERROR_PARAMETER_NOT_PRESENT, "on method sort(dto)");
            });

    return Optional.ofNullable(dto.getSortStrategy())
        .map(strategy -> strategy.sort(dto))
        .orElseGet(() -> SortTeamStrategyEnum.SKILL_LEVEL.sort(dto));
  }
}
