package com.paulrps.peladator.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.services.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	PlayerService playerService;

	@GetMapping("/{id}")
	Player getPlayer(@PathVariable(value="id") Long id) {
		
		Player p = new Player();
		p.setName("Joao");
		p.setAge(30);
		
		playerService.addPlayer(p);
		
		return playerService.getOnePlayer(id);
	}
	
	@GetMapping("/")
	List<Player> getAllPlayers() {
		return playerService.getAllPlayers();
	}
	
	@PostMapping("/")
	Player addPlayer(Player player) {
		return playerService.addPlayer(player);		
	}
	
}
