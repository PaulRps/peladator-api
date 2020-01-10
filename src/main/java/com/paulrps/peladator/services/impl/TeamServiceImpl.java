package com.paulrps.peladator.services.impl;

import com.paulrps.peladator.domain.dto.SortTeamDto;
import com.paulrps.peladator.domain.dto.TeamDto;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.enums.PlayerPositionEnum;
import com.paulrps.peladator.services.TeamService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    @Override
    public List<TeamDto> sort(SortTeamDto dto) {
        List<TeamDto> teams = new ArrayList<>();
        List<Integer> teamsOrder = new ArrayList<>();
        for (int i = 0; i < dto.getAmount(); i++) {
            teams.add(TeamDto.builder().name("Time "+ (i+1)).players(new ArrayList<>()).build());
            teamsOrder.add(i);
        }

        Map<@NotNull PlayerPositionEnum, List<Player>> playersByPosition = dto.getPlayers().stream()
                                                                                .collect(Collectors.groupingBy(Player::getPosition));
        playersByPosition.forEach((pos,players) -> {
            boolean nextPos = false;
            Collections.sort(players, Comparator.comparing(Player::getSkillLevel).reversed());
            for (int i = 0; i < players.size() && !nextPos;) {
                List<Integer> teamsOrderClone = new ArrayList<>(teamsOrder);
                for (int j = 0; j < dto.getAmount(); j++) {
                    Collections.shuffle(teamsOrderClone, new Random());
                    Integer randomPos = teamsOrderClone.remove(0);
                    if (i < players.size()) {
                        teams.get(randomPos).getPlayers().add(players.get(i));
                    }
                    i++;
                    if (PlayerPositionEnum.GK.isEqual(pos) && j+1 == dto.getAmount()) {
                        nextPos = true;
                        break;
                    }
                }
            }
        });
        
        teams.forEach(team -> {
            team.getPlayers().sort(Comparator.comparing(Player::getPosition));
        });
        return teams;
    }
}
