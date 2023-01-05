package com.example.rps.player;

import lombok.Value;

import java.util.UUID;

@Value
public class PlayerDTO {

    UUID id;
    String name;
}
