package com.example.rps.player;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "players")
@Table(name = "Players")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerEntity {

    @Id
    private UUID playerId;
    private String name;
    private String opponentName;

    /*
    public PlayerEntity(UUID playerId) {        //Constructor with only id, for use in setting id at start
        this.playerId = playerId;
    }

    public PlayerEntity(UUID playerId, String name) {
        this.playerId = playerId;
        this.name = name;
    } */
}
