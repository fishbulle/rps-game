package com.example.rps.game;

import com.example.rps.player.PlayerEntity;
import lombok.*;

import java.util.UUID;

@Value
public class GameStatus {

    // DTO

    UUID gameId;
    PlayerEntity name;
    Move move;
    PlayerEntity opponentName;
    Move opponentMove;
    Status status;

}
