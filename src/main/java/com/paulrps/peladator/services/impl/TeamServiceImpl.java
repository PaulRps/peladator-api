package com.paulrps.peladator.services.impl;

import com.paulrps.peladator.domain.dto.TeamsDto;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.services.TeamService;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TeamServiceImpl implements TeamService {

    @Override
    public TeamsDto sort(List<Player> players) {
        List<Player> teamOne = new ArrayList<>();
        List<Player> teamTwo = new ArrayList<>();
        boolean one = true;
        for (Player p : players) {
            if (one) {
                teamOne.add(p);
                one = false;
            } else {
                teamTwo.add(p);
                one = true;
            }
        }
        return TeamsDto.builder()
                .data(
                        Stream.of(
                                new AbstractMap.SimpleEntry<>("one", teamOne),
//							new AbstractMap.SimpleEntry<>("four", teamOne),
//							new AbstractMap.SimpleEntry<>("three", teamOne),
                                new AbstractMap.SimpleEntry<>("two", teamTwo))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                .build();
    }
}
