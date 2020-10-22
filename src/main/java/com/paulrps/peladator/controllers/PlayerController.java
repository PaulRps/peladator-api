package com.paulrps.peladator.controllers;

import com.paulrps.peladator.domain.dto.PlayerFormDto;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.services.PlayerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("player")
public class PlayerController {

  private static PlayerService playerService;

  @Autowired
  PlayerController(final PlayerService playerService) {
    PlayerController.playerService = playerService;
  }

  @PreAuthorize("permitAll()")
  @GetMapping("form-data")
  public ResponseEntity<PlayerFormDto> getFormData() {
    return ResponseEntity.ok(playerService.formData());
  }

  @PreAuthorize("permitAll()")
  @GetMapping
  public ResponseEntity<List<Player>> getAllPlayers() {
    return ResponseEntity.ok(playerService.findAll());
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping
  public ResponseEntity<Player> save(@RequestBody Player player) {
    return new ResponseEntity<>(playerService.save(player), HttpStatus.CREATED);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping
  public ResponseEntity<Void> update(@RequestBody Player player) {
    playerService.update(player);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
    playerService.delete(id);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
}
