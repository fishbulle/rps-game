package com.example.rps.game;

import com.example.rps.player.PlayerEntity;

import java.util.UUID;

public record GameStatus(UUID gameId,
                         PlayerEntity playerOne,
                         Move playerOneMove,
                         PlayerEntity playerTwo,
                         Move playerTwoMove,
                         Status status,
                         Result result) {

}