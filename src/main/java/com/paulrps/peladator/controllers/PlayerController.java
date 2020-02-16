package com.paulrps.peladator.controllers;

import com.paulrps.peladator.domain.dto.PlayerFormDto;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.services.PlayerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("player")
public class PlayerController {

  @Autowired PlayerService playerService;

  @GetMapping("form-data")
  ResponseEntity<PlayerFormDto> getFormData() {
    return ResponseEntity.ok(
        PlayerFormDto.builder()
            .positions(playerService.getPlayerPositions())
            .skillLevels(playerService.getPlayerLevels())
            .build());
  }

  @GetMapping
  ResponseEntity<List<Player>> getAllPlayers() {
    return ResponseEntity.ok(playerService.getAll());
  }

  @PostMapping
  ResponseEntity<List<Player>> save(@RequestBody Player player) {
    playerService.save(player);
    return ResponseEntity.ok(playerService.getAll());
  }

  @PutMapping
  ResponseEntity<List<Player>> update(@RequestBody Player player) {
    playerService.update(player);
    return ResponseEntity.ok(playerService.getAll());
  }

  @DeleteMapping("{id}")
  ResponseEntity<List<Player>> delete(@PathVariable(value = "id") Long id) {
    playerService.delete(id); // TODO: validar delecao
    return ResponseEntity.ok(playerService.getAll());
  }
}
