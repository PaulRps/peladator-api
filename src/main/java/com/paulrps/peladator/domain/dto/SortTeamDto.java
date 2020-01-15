package com.paulrps.peladator.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.enums.SortTeamStrategyEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SortTeamDto {
    private Integer amount;
    private Integer teamSize;
    private List<SortTeamStrategyEnum> sortStrategies;
    private SortTeamStrategyEnum sortStrategy;
    private List<Player> players;
    private Map<String, List<Player>> playersGroupedByPosition;
}
