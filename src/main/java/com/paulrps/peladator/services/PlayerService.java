package com.paulrps.peladator.services;

import java.util.List;

import com.paulrps.peladator.domain.dto.TeamsDto;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.enums.PlayerLevelEnum;
import com.paulrps.peladator.domain.enums.PlayerPositionEnum;

public interface PlayerService {

	public Player save(Player player);
	
	public boolean delete(Long id);

	public List<Player> getAll();
	
	public Player getOne(Long id);
	
	public List<PlayerPositionEnum> getPlayerPositions();

	public List<PlayerLevelEnum> getPlayerLevels();

    public void update(Player player);
}
