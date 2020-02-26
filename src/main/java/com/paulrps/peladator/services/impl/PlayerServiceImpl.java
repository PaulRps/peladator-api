package com.paulrps.peladator.services.impl;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import com.paulrps.peladator.domain.entities.Payment;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.enums.PlayerLevelEnum;
import com.paulrps.peladator.domain.enums.PlayerPositionEnum;
import com.paulrps.peladator.repositories.PlayerResository;
import com.paulrps.peladator.services.PaymentService;
import com.paulrps.peladator.services.PlayerService;
import java.util.*;
import java.util.stream.Stream;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

  @Autowired PlayerResository playerResository;
  @Autowired PaymentService paymentService;

  @Override
  public Player save(Player player) {
    if (!Optional.ofNullable(player).isPresent()) {
      throw new RuntimeException(""); // TODO: define structure of messages
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
    List<Player> players = playerResository.findAll();
    Map<@NotNull Player, List<Payment>> playerListMap =
        paymentService.findByPlayersAndDate(new Date(), players).stream()
            .collect(groupingBy(Payment::getPlayer));

    return players.stream()
        .map(
            p -> {
              List<Payment> payments =
                  Optional.ofNullable(playerListMap.get(p)).orElse(new ArrayList<>());
              if (!payments.isEmpty()) {
                p.setPaymentDate(payments.get(0).getDate());
              }
              return p;
            })
        .collect(toList());
  }

  @Override
  public List<PlayerPositionEnum> getPlayerPositions() {
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
        .forEach(
            p -> {
              positionMap.put(p.getName(), new ArrayList<>());
            });
    getAll().stream()
        .forEach(
            p -> {
              positionMap.get(p.getPosition().getName()).add(p);
            });
    return positionMap;
  }
}
