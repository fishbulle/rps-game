package com.example.rps.player;

import com.example.rps.game.GameEntity;

public record UpdatePlayer(String username,
                           GameEntity playerOneGame,
                           GameEntity playerTwoGame) {}
