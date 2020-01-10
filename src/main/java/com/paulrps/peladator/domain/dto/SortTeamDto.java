package com.paulrps.peladator.domain.dto;

import com.paulrps.peladator.domain.entities.Player;
import lombok.Data;

import java.util.List;

@Data
public class SortTeamDto {
    private Integer amount;
    private List<Player> players;
}
