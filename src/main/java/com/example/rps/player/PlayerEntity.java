package com.example.rps.player;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "players")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Players")
public class PlayerEntity {

    @Id
    private UUID playerId;  // deklarera UUID.randomUUID redan här eller i serviceklass?
    private String name;
    private String opponentName;

    public PlayerEntity(UUID playerId) {
        this.playerId = playerId;
    }

    public PlayerEntity(UUID playerId, String name) {
        this.playerId = playerId;
        this.name = name;
    }
}
