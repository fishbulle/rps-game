package com.example.rps.game;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameRepository extends JpaRepository<GameEntity, UUID> {
}
