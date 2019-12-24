package com.paulrps.peladator.services.impl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.paulrps.peladator.domain.dto.TeamsDto;
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
			throw new RuntimeException("");//TODO: define structure of messages
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
	public List<Player> getAll() {
		return playerResository.findAll();
	}

	@Override
	public Player getOne(Long id) {
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

	@Override
	public void update(Player player) {
		playerResository.save(player);
	}

	@Override
	public TeamsDto sortTeams(List<Player> players) {
		List<Player> teamOne = new ArrayList<>();
		List<Player> teamTwo = new ArrayList<>();
		boolean one = true;
		for (Player p : players) {
			if (one) {
				teamOne.add(p);
				one = false;
			} else {
				teamTwo.add(p);
				one = true;
			}
		}
		return TeamsDto.builder()
				.data(
					Stream.of(
							new AbstractMap.SimpleEntry<>("one", teamOne),
							new AbstractMap.SimpleEntry<>("four", teamOne),
							new AbstractMap.SimpleEntry<>("three", teamOne),
							new AbstractMap.SimpleEntry<>("two", teamTwo))
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
				.build();
	}
}
