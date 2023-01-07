package com.example.rps.game;

import com.example.rps.player.PlayerEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
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
