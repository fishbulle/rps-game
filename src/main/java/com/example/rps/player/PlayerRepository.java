package com.example.rps.player;

import com.example.rps.player.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer> {
}
