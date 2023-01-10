package com.example.rps.player;

import com.example.rps.game.GameEntity;
import lombok.Value;

@Value
public class UpdatePlayer {

    String name;
    GameEntity playerOneGame;
    GameEntity playerTwoGame;

}
