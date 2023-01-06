package com.example.rps.player;

import lombok.Value;

@Value
public class UpdatePlayer {

    // DTO - vi vill bara kunna uppdatera namnet, inte ID

    String name;
    String opponentName;

}
