package com.paulrps.peladator.domain.enums;

import static java.util.stream.Collectors.toList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paulrps.peladator.domain.dto.SortTeamDto;
import com.paulrps.peladator.domain.dto.TeamDto;
import com.paulrps.peladator.domain.entities.Player;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum SortTeamStrategyEnum implements EnumInterface {
  SKILL_LEVEL(0, "Nível") {
    @Override
    public List<TeamDto> sort(SortTeamDto dto) {
      List<TeamDto> teams = new ArrayList<>();
      List<Integer> teamsOrder = new ArrayList<>();
      for (int i = 0; i < dto.getAmount(); i++) {
        teams.add(TeamDto.builder().name("Time " + (i + 1)).players(new ArrayList<>()).build());
        teamsOrder.add(i);
      }
      Collections.sort(dto.getPlayers(), Comparator.comparing(Player::getSkillLevel).reversed());

      Predicate<Player> gkFilter = p -> PlayerPositionEnum.GK.isEqual(p.getPosition());

      this.addPlayersToTeams(
          dto.getPlayers().stream().filter(gkFilter).collect(toList()),
          dto.getAmount(),
          dto.getTeamSize(),
          teams,
          new ArrayList<>(teamsOrder));
      dto.getPlayers().removeIf(gkFilter);

      this.addPlayersToTeams(
          dto.getPlayers(), dto.getAmount(), dto.getTeamSize(), teams, teamsOrder);

      teams.forEach(team -> team.getPlayers().sort(Comparator.comparing(Player::getPosition)));
      return teams;
    }
  },
  POSITION(1, "Posição") {
    @Override
    public List<TeamDto> sort(SortTeamDto dto) {
      List<TeamDto> teams = new ArrayList<>();
      List<Integer> teamsOrder = new ArrayList<>();
      for (int i = 0; i < dto.getAmount(); i++) {
        teams.add(TeamDto.builder().name("Time " + (i + 1)).players(new ArrayList<>()).build());
        teamsOrder.add(i);
      }
      Map<@NotNull PlayerPositionEnum, List<Player>> playersByPosition =
          dto.getPlayers().stream().collect(Collectors.groupingBy(Player::getPosition));
      playersByPosition.forEach(
          (pos, players) -> {
            Collections.sort(players, Comparator.comparing(Player::getSkillLevel).reversed());
            addPlayersToTeams(
                players, dto.getAmount(), dto.getTeamSize(), teams, new ArrayList<>(teamsOrder));
          });
      teams.forEach(team -> team.getPlayers().sort(Comparator.comparing(Player::getPosition)));
      return teams;
    }
  };

  private Integer id;
  private String name;

  @JsonCreator
  public static SortTeamStrategyEnum forValues(
      @JsonProperty("id") Integer id, @JsonProperty("name") String name) {
    return (SortTeamStrategyEnum) SortTeamStrategyEnum.POSITION.getOne(id, name);
  }

  public abstract List<TeamDto> sort(SortTeamDto dto);

  void addPlayersToTeams(
      List<Player> players,
      Integer amount,
      Integer teamSize,
      List<TeamDto> teams,
      List<Integer> teamsOrder) {
    for (int i = 0; i < players.size(); ) {
      List<Integer> teamsOrderClone = new ArrayList<>(teamsOrder);
      if (teamsOrderClone.isEmpty()) {
        List<Player> benchPlayers = players.subList(i, players.size());
        if (teams.size() == amount && !benchPlayers.isEmpty()) {
          teams.add(TeamDto.builder().name("Reserva").players(benchPlayers).build());
        } else if (!benchPlayers.isEmpty()) {
          teams.get(teams.size() - 1).getPlayers().addAll(benchPlayers);
        }
        return;
      }
      for (int j = 0; j < amount; j++) {
        Collections.shuffle(teamsOrderClone, new Random());
        Integer randomPos = teamsOrderClone.remove(0);
        if (i < players.size()) {
          teams.get(randomPos).getPlayers().add(players.get(i));
          if (teams.get(randomPos).getPlayers().size() == teamSize) {
            teamsOrder.remove(randomPos);
          }
        }
        i++;
        if (i < players.size()
            && PlayerPositionEnum.GK.isEqual(players.get(i).getPosition())
            && j + 1 == amount) {
          List<Player> benchPlayers = players.subList(i, players.size());
          if (teams.size() == amount && !benchPlayers.isEmpty()) {
            teams.add(TeamDto.builder().name("Reserva").players(benchPlayers).build());
          } else if (!benchPlayers.isEmpty()) {
            teams.get(teams.size() - 1).getPlayers().addAll(benchPlayers);
          }
          return;
        }
      }
    }
  }

  public List<EnumInterface> getValues() {
    return Arrays.asList(SortTeamStrategyEnum.values());
  }
}
