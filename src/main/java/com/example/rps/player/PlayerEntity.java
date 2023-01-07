package com.example.rps.player;

import com.example.rps.game.GameEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column(name = "player_id")
    private UUID playerId;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "playerOne")  // mappar ihop GameEntity med PlayerEntity playerOne
    @JsonIgnoreProperties("playerOne")
    private GameEntity playerOne;

    @OneToOne(mappedBy = "playerTwo")
    @JsonIgnoreProperties("playerTwo")
    private GameEntity playerTwo;

    public PlayerEntity(UUID playerId) {
        this.playerId = playerId;
    }

    public PlayerEntity(UUID playerId, String name, GameEntity playerOne) {
        this.playerId = playerId;
        this.name = name;
        this.playerOne = playerOne;
    }
}
