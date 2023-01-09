package com.example.rps.player;

import com.example.rps.game.GameEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlayerEntity {

    @Id
    @Column(name = "player_id")
    private UUID playerId;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "playerOne")  // mappar ihop GameEntity med PlayerEntity playerOne
    //@JsonIgnoreProperties("playerOne")
    @JsonIgnore
    private GameEntity playerOneGame;

    @OneToOne(mappedBy = "playerTwo")
    //@JsonIgnoreProperties("playerTwo")
    @JsonIgnore
    private GameEntity playerTwoGame;

    public PlayerEntity(UUID playerId) {
        this.playerId = playerId;
    }

    public PlayerEntity(UUID playerId, String name, GameEntity playerOneGame) {
        this.playerId = playerId;
        this.name = name;
        this.playerOneGame = playerOneGame;
    }
}
