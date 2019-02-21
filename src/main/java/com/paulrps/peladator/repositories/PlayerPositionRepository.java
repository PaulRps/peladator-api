package com.paulrps.peladator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paulrps.peladator.domain.entities.PlayerPosition;

@Repository
public interface PlayerPositionRepository extends JpaRepository<PlayerPosition, Long> {

}
