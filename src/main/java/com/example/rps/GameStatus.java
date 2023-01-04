package com.example.rps;

import lombok.*;

@Value
public class GameStatus {

    // DTO

    String id;
    String name;
    Move move;
    String opponentName;
    Move opponentMove;
    Status status;

}
