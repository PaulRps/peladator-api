package com.paulrps.peladator.services;

import com.paulrps.peladator.domain.dto.PlayerFormDto;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.enums.PlayerLevelEnum;
import com.paulrps.peladator.domain.enums.PlayerPositionEnum;
import java.util.List;
import java.util.Map;

public interface PlayerService {

  Player save(Player player);

  Player update(Player player);

  boolean delete(Long id);

  Player find(Long id);

  List<Player> findAll();

  List<PlayerPositionEnum> getPlayerPositions();

  List<PlayerLevelEnum> getPlayerLevels();

  Map<String, List<Player>> groupByPositionAndSort();

  PlayerFormDto formData();
}
