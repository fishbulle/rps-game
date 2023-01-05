package com.example.rps.game;

import com.example.rps.game.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameEntity, Integer> {
}
