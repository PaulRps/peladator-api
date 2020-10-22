package com.paulrps.peladator.services.impl;

import static java.util.stream.Collectors.groupingBy;

import com.paulrps.peladator.config.exceptions.ApiException;
import com.paulrps.peladator.config.exceptions.ApiMessageEnum;
import com.paulrps.peladator.domain.dto.PlayerFormDto;
import com.paulrps.peladator.domain.entities.Payment;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.enums.PlayerLevelEnum;
import com.paulrps.peladator.domain.enums.PlayerPositionEnum;
import com.paulrps.peladator.repositories.PlayerResository;
import com.paulrps.peladator.services.PaymentService;
import com.paulrps.peladator.services.PlayerService;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {

  private static PlayerResository playerResository;
  private static PaymentService paymentService;

  @Autowired
  PlayerServiceImpl(final PlayerResository playerResository, final PaymentService paymentService) {
    PlayerServiceImpl.playerResository = playerResository;
    PlayerServiceImpl.paymentService = paymentService;
  }

  @Override
  public Player save(Player player) {

    Optional.ofNullable(player)
        .orElseThrow(
            () -> {
              log.error("Parameter player is null");
              return new ApiException(ApiMessageEnum.ERROR_PARAMETER_NOT_PRESENT, "player");
            });

    try {

      log.debug("creating player {}", player.toString());
      return playerResository.save(player);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      log.error(player.toString());
      throw new ApiException(ApiMessageEnum.ERROR_ON_SAVE_ENTITY, e, player.toString());
    }
  }

  @Override
  public void update(Player player) {

    Optional.ofNullable(player)
        .orElseThrow(
            () -> {
              log.error("Parameter player is null");
              return new ApiException(ApiMessageEnum.ERROR_PARAMETER_NOT_PRESENT, "player");
            });

    Optional.ofNullable(player.getId())
        .orElseThrow(
            () -> {
              log.error("PLAYER_ID parameter is null");
              return new ApiException(ApiMessageEnum.ERROR_PARAMETER_NOT_PRESENT, "PLAYER_ID");
            });

    try {
      log.debug("updating player {}}", player.toString());
      save(player);
      log.debug("updated player");
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ApiException(ApiMessageEnum.ERROR_ON_SAVE_ENTITY, e, player.toString());
    }
  }

  @Override
  public void delete(Long id) {

    Optional.ofNullable(id)
        .orElseThrow(
            () -> {
              log.error("PLAYER_ID parameter is null");
              return new ApiException(ApiMessageEnum.ERROR_PARAMETER_NOT_PRESENT, "PLAYER_ID");
            });

    if (!playerResository.existsById(id)) {
      log.error("Player[id={}] do not exist", id);
      throw new ApiException(
          ApiMessageEnum.ERROR_RESOURCE_NOT_FOUND, String.format("Player[id=%d]", id));
    }

    try {
      log.debug("deleting player [id={}]", id);
      playerResository.deleteById(id);
      log.debug("player deleted");
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ApiException(
          ApiMessageEnum.ERROR_ON_DELETE_ENTITY, e, String.format("Player[id=%d]", id));
    }
  }

  @Override
  public Player find(Long id) {
    Optional.ofNullable(id)
        .orElseThrow(
            () ->
                new ApiException(ApiMessageEnum.ERROR_PARAMETER_NOT_PRESENT, "PLAYER_ID is null"));

    try {
      log.debug("quering Player[id={}]", id);
      return playerResository.findById(id).orElse(null);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ApiException(
          ApiMessageEnum.ERROR_RESOURCE_NOT_FOUND, e, String.format("Player[id=%d]", id));
    }
  }

  @Override
  public List<Player> findAll() {
    try {
      log.debug("querying all players");

      List<Player> players = playerResository.findAll();

      log.debug("queried {} players", players.size());

      log.debug("querying player's payment");
      final Map<@NotNull Player, List<Payment>> playerListMap =
          paymentService.findAll(LocalDate.now().getMonthValue(), players).stream()
              .collect(groupingBy(Payment::getPlayer));

      log.debug("setting player's payments");
      players.stream()
          .forEach(
              p -> {
                List<Payment> payments =
                    Optional.ofNullable(playerListMap.get(p)).orElse(new ArrayList<>());
                if (!payments.isEmpty()) {
                  p.setPaymentDate(payments.get(0).getDate());
                }
              });
      return players;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ApiException(ApiMessageEnum.ERROR_INTERNAL_SERVER, e, "getAll players");
    }
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
    try {
      log.debug("grouping players by position");
      Map<String, List<Player>> positionMap = new LinkedHashMap<>();
      Stream.of(PlayerPositionEnum.values())
          .sorted(Comparator.comparingInt(PlayerPositionEnum::getId))
          .forEach(p -> positionMap.put(p.getName(), new ArrayList<>()));
      findAll().forEach(p -> positionMap.get(p.getPosition().getName()).add(p));
      log.debug("{} }players were grouped", positionMap.size());
      return positionMap;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ApiException(ApiMessageEnum.ERROR_INTERNAL_SERVER, e, "group players by position");
    }
  }

  @Override
  public PlayerFormDto formData() {
    try {
      return PlayerFormDto.builder()
          .positions(getPlayerPositions())
          .skillLevels(getPlayerLevels())
          .build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ApiException(ApiMessageEnum.ERROR_INTERNAL_SERVER, e, "get player form data");
    }
  }
}
