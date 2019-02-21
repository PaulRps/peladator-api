package com.paulrps.peladator.services;

import java.util.List;

import com.paulrps.peladator.domain.entities.Player;

public interface PlayerService {

	public Player addPlayer(Player player);
	
	public boolean deletePlayer(Long id);

	public List<Player> getAllPlayers();
	
	public Player getOnePlayer(Long id);
}
