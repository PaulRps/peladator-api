package com.paulrps.peladator.services;

import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.enums.PlayerLevelEnum;
import com.paulrps.peladator.domain.enums.PlayerPositionEnum;

import java.util.List;
import java.util.Map;

public interface PlayerService {

	public Player save(Player player);

	public void update(Player player);

	public boolean delete(Long id);

	public Player getOne(Long id);

	public List<Player> getAll();

	public List<PlayerPositionEnum> getPlayerPositions();

	public List<PlayerLevelEnum> getPlayerLevels();

	public Map<String, List<Player>> groupByPositionAndSort();
}
