package com.paulrps.peladator.repositories;

import com.paulrps.peladator.domain.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerResository extends JpaRepository<Player, Long> {}
