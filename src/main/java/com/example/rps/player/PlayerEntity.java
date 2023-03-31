package com.example.rps.player;

import com.example.rps.game.GameEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;
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

    @OneToMany(mappedBy = "playerOne")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<GameEntity> playerOneGame;

    @OneToMany(mappedBy = "playerTwo")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<GameEntity> playerTwoGame;

    public PlayerEntity(UUID playerId) {
        this.playerId = playerId;
    }

    public void setPlayerOneGame(GameEntity playerOneGame) {
        this.playerOneGame.add(playerOneGame);
    }

    public void setPlayerTwoGame(GameEntity playerTwoGame) {
        this.playerOneGame.add(playerTwoGame);
    }

}