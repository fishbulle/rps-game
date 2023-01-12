package com.example.rps.player;

import com.example.rps.game.GameEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "players")
@Table(name = "Players")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlayerEntity {

    @Id
    @Column(name = "player_id")
    private UUID playerId;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "playerOne")
    @JsonIgnore
    private GameEntity playerOneGame;

    @OneToOne(mappedBy = "playerTwo")
    @JsonIgnore
    private GameEntity playerTwoGame;

    public PlayerEntity(UUID playerId) {
        this.playerId = playerId;
    }
}
