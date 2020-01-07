package com.paulrps.peladator.controllers;

import com.paulrps.peladator.domain.dto.PlayerFormDto;
import com.paulrps.peladator.domain.dto.TeamsDto;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.enums.PlayerPositionEnum;
import com.paulrps.peladator.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
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
	Map<PlayerPositionEnum, List<Player>> getByPosition() {
		Map<PlayerPositionEnum, List<Player>> positionMap = new TreeMap<>();
		Stream.of(PlayerPositionEnum.values()).forEach(p -> {
			positionMap.put(p, new ArrayList<>());
		});

		playerService.getAll()
				.stream()
				.forEach(p -> {
					positionMap.get(p.getPosition()).add(p);
				});

		return positionMap;
	}
}
