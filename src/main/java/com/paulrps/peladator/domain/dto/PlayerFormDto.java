package com.paulrps.peladator.domain.dto;

import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.enums.PlayerLevelEnum;
import com.paulrps.peladator.domain.enums.PlayerPositionEnum;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerFormDto {

  private List<PlayerPositionEnum> positions;
  private List<PlayerLevelEnum> skillLevels;
  private Player player;
}
