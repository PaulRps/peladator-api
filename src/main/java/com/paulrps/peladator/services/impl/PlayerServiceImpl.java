package com.paulrps.peladator.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.enums.PlayerLevelEnum;
import com.paulrps.peladator.domain.enums.PlayerPositionEnum;
import com.paulrps.peladator.repositories.PlayerResository;
import com.paulrps.peladator.services.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	PlayerResository playerResository;
	
	@Override
	public Player save(Player player) {
		if (!Optional.ofNullable(player).isPresent()) {
			throw new RuntimeException("");
		}
		
		if (Optional.ofNullable(player.getId()).isPresent()) {
			Optional<Player> findById = playerResository.findById(player.getId());
			if (findById.isPresent()) {
				return player;
			}
		}
		return playerResository.save(player);
	}	
	
	@Override
	public boolean delete(Long id) {
		if (!Optional.ofNullable(id).isPresent()) {
			throw new RuntimeException("");
		}

		Optional<Player> player = playerResository.findById(id);
		if (player.isPresent()) {
			playerResository.delete(player.get());
			return true;
		}
		return false;
	}

	@Override
	public List<Player> getAllPlayers() {
		return playerResository.findAll();
	}

	@Override
	public Player getOnePlayer(Long id) {
		if (!Optional.ofNullable(id).isPresent()) {
			throw new RuntimeException("");
		}
		return playerResository.findById(id).orElse(null);
	}
	
	@Override
	public List<PlayerPositionEnum> getPlayerPositions() {
		PlayerPositionEnum.class.getEnumConstants();
		return Arrays.asList(PlayerPositionEnum.values());
	}
	
	@Override
	public List<PlayerLevelEnum> getPlayerLevels() {
		return Arrays.asList(PlayerLevelEnum.values());
	}
}
