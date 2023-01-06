package com.example.rps.game;

import lombok.*;

import java.util.UUID;

@Value
public class GameStatus {

    // DTO

    UUID gameId;
    String name;
    Move move;
    String opponentName;
    Move opponentMove;
    Status status;

}
