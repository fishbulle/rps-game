package com.example.rps.player;

import com.example.rps.game.GameEntity;

public record UpdatePlayer(String name,
                           GameEntity playerOneGame,
                           GameEntity playerTwoGame) {}
