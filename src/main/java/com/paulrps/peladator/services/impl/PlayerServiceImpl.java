package com.paulrps.peladator.services.impl;

import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.enums.PlayerLevelEnum;
import com.paulrps.peladator.domain.enums.PlayerPositionEnum;
import com.paulrps.peladator.repositories.PlayerResository;
import com.paulrps.peladator.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	PlayerResository playerResository;
	
	@Override
	public Player save(Player player) {
		if (!Optional.ofNullable(player).isPresent()) {
			throw new RuntimeException("");//TODO: define structure of messages
		}
		return playerResository.save(player);
	}

	@Override
	public void update(Player player) {
		playerResository.save(player);
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
	public Player getOne(Long id) {
		if (!Optional.ofNullable(id).isPresent()) {
			throw new RuntimeException("");
		}
		return playerResository.findById(id).orElse(null);
	}

	@Override
	public List<Player> getAll() {
		return playerResository.findAll();
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

	@Override
	public Map<String, List<Player>> groupByPositionAndSort() {
		Map<String, List<Player>> positionMap = new LinkedHashMap<>();
		Stream.of(PlayerPositionEnum.values())
				.sorted(Comparator.comparingInt(PlayerPositionEnum::getId))
				.forEach(p -> {
					positionMap.put(p.getName(), new ArrayList<>());
				});
		getAll().stream()
				.forEach(p -> {
					positionMap.get(p.getPosition().getName()).add(p);
				});
		return positionMap;
	}
}
