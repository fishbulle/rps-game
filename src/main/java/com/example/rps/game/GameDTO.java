package com.example.rps.game;

import lombok.*;

@Value
public class GameDTO {

    // DTO

    String gameId;
    String name;
    Move move;
    String opponentName;
    Move opponentMove;
    Status status;
    Result result;

}
