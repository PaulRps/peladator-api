package com.paulrps.peladator.repositories;

import com.paulrps.peladator.domain.entities.PlayerPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerPositionRepository extends JpaRepository<PlayerPosition, Long> {

}
