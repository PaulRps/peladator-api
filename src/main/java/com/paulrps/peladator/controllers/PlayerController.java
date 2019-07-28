package com.paulrps.peladator.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.paulrps.peladator.domain.dto.PlayerAddDataViewDto;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.services.PlayerService;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	PlayerService playerService;

	@GetMapping("/{id}")
	Player getPlayer(@PathVariable(value="id") Long id) {
		
		return playerService.getOnePlayer(id);
	}
	
	@GetMapping("/view-add-data")
	PlayerAddDataViewDto getPlayerPositions() {
		
		PlayerAddDataViewDto dto = new PlayerAddDataViewDto();
		
		dto.setPositions(playerService.getPlayerPositions());
		dto.setSkillLevels(playerService.getPlayerLevels());
		
		return dto;
	}
	
	@GetMapping
	List<Player> getAllPlayers() {
		return playerService.getAllPlayers();
	}
	
	@PostMapping
	List<Player> addPlayer(@RequestBody Player player) {
		
		playerService.addPlayer(player);
		
		return playerService.getAllPlayers();		
	}

	@DeleteMapping("/{id}")
	List<Player> deletePlayer(@PathVariable(value="id") Long id) {

		playerService.deletePlayer(id);

		return playerService.getAllPlayers();
	}
	
}
