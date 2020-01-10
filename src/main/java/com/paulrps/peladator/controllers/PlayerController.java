package com.paulrps.peladator.controllers;

import com.paulrps.peladator.domain.dto.PlayerFormDto;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.enums.PlayerPositionEnum;
import com.paulrps.peladator.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Stream;

@CrossOrigin(origins = {"http://localhost:4200", "https://peladator.netlify.com"}, maxAge = 3600)
@RestController
@RequestMapping("player")
public class PlayerController {
	
	@Autowired
	PlayerService playerService;

	@GetMapping("{id}")
	Player getPlayer(@PathVariable(value="id") Long id) {
		return playerService.getOne(id);
	}
	
	@GetMapping("form-data")
	PlayerFormDto getFormData() {
		return PlayerFormDto.builder()
					.positions(playerService.getPlayerPositions())
					.skillLevels(playerService.getPlayerLevels())
					.build();
	}
	
	@GetMapping
	List<Player> getAllPlayers() {
		return playerService.getAll();
	}
	
	@PostMapping
	List<Player> save(@RequestBody Player player) {
		playerService.save(player);
		return playerService.getAll();
	}

	@PutMapping
	List<Player> update(@RequestBody Player player) {
		playerService.update(player);
		return playerService.getAll();
	}

	@DeleteMapping("{id}")
	List<Player> delete(@PathVariable(value="id") Long id) {
		playerService.delete(id);//TODO: validar delecao
		return playerService.getAll();
	}

	@GetMapping("groupby-position")
	Map<String, List<Player>> getByPosition() {
		Map<String, List<Player>> positionMap = new LinkedHashMap<>();
		Stream.of(PlayerPositionEnum.values())
				.sorted(Comparator.comparingInt(PlayerPositionEnum::getId))
				.forEach(p -> {
			positionMap.put(p.getName(), new ArrayList<>());
		});

		playerService.getAll()
				.stream()
				.forEach(p -> {
					positionMap.get(p.getPosition().getName()).add(p);
				});

		return positionMap;
	}
}
