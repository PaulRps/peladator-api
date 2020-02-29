package com.paulrps.peladator.domain.dto;

import com.paulrps.peladator.domain.entities.Player;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamDto {
  private String name;
  private List<Player> players;
}
