package com.example.rps.game;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "game")
@Table(name = "Game")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity {

    @Id
    @Column(name = "game_id")
    private UUID gameId;
    Status status;
    String name;
    String opponentName;

}