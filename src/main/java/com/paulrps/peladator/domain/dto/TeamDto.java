package com.paulrps.peladator.domain.dto;

import com.paulrps.peladator.domain.entities.Player;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class TeamDto {
    private String name;
    private List<Player> players;
}
