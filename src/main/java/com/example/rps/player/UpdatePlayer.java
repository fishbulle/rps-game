package com.example.rps.player;

import com.example.rps.game.GameEntity;
import lombok.Value;

@Value
public class UpdatePlayer {

    // DTO - vi vill bara kunna uppdatera namnet, inte ID

    String name;
    GameEntity playerOneGame;
    GameEntity playerTwoGame;

}
