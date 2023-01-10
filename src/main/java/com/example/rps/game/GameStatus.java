package com.example.rps.game;

import com.example.rps.player.PlayerEntity;

import java.util.UUID;

public record GameStatus(UUID gameId,
                         PlayerEntity name,
                         Move move,
                         PlayerEntity opponentName,
                         Move opponentMove,
                         Status status) {

}
