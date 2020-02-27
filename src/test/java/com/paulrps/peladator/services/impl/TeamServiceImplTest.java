package com.paulrps.peladator.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.paulrps.peladator.domain.dto.SortTeamDto;
import com.paulrps.peladator.domain.dto.TeamDto;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.enums.PlayerLevelEnum;
import com.paulrps.peladator.domain.enums.PlayerPositionEnum;
import com.paulrps.peladator.domain.enums.SortTeamStrategyEnum;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TeamServiceImplTest {

  @Mock PlayerServiceImpl playerService;
  @InjectMocks TeamServiceImpl service;

  @Test
  void loadTeamsPage() {
    // given
    Map<String, List<Player>> map = new LinkedHashMap<>();
    map.put(PlayerPositionEnum.GK.getName(), Arrays.asList(Player.builder().id(1l).build()));
    map.put(PlayerPositionEnum.ATA.getName(), Arrays.asList(Player.builder().id(2l).build()));
    given(playerService.groupByPositionAndSort()).willReturn(map);

    //    when
    SortTeamDto sortTeamDto = service.loadTeamsPage();
    //    then
    assertThat(sortTeamDto).isNotNull();
    assertThat(sortTeamDto.getSortStrategies())
        .isNotNull()
        .isNotEmpty()
        .hasSize(SortTeamStrategyEnum.values().length - 1);
    assertThat(sortTeamDto.getPlayersGroupedByPosition())
        .isNotNull()
        .isNotEmpty()
        .hasSize(map.size());
  }

  @Test
  void sort() {

    //    given
    Player p1 =
        Player.builder()
            .position(PlayerPositionEnum.ATA)
            .skillLevel(PlayerLevelEnum.JOGA_MUITO)
            .build();
    Player p2 =
        Player.builder()
            .position(PlayerPositionEnum.ATA)
            .skillLevel(PlayerLevelEnum.JOGA_MUITO)
            .build();
    SortTeamDto dto =
        SortTeamDto.builder()
            .amount(2)
            .teamSize(1)
            .players(Arrays.asList(p1, p2))
            .sortStrategy(SortTeamStrategyEnum.POSITION)
            .build();

    List<TeamDto> teams =
        Arrays.asList(
            TeamDto.builder().name("Time 1").players(Arrays.asList(p1)).build(),
            TeamDto.builder().name("Time 2").players(Arrays.asList(p2)).build());

    //    when
    List<TeamDto> sort = service.sort(dto);

    dto.setSortStrategy(null);
    List<TeamDto> sort1 = service.sort(dto);

    //    then
    assertThat(sort).isNotNull().isNotEmpty().hasSize(teams.size());
    assertThat(sort1).isNotNull().isNotEmpty().hasSize(teams.size());
    assertThrows(RuntimeException.class, () -> service.sort(null));
  }
}
