package com.example.rps.game;

import com.example.rps.player.PlayerEntity;
import jakarta.persistence.*;
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

    @OneToOne
    @JoinColumn(name = "playerOne")
    PlayerEntity playerOne;

    @Column(name = "player_move")
    @Enumerated(EnumType.STRING)  // enumerated gör så att det står med bokstäver istället för siffror
    Move playerMove;              // tex istället för 1 står det ROCK

    @OneToOne
    @JoinColumn(name = "playerTwo")
    PlayerEntity playerTwo;

    @Column(name = "opponent_move")
    @Enumerated(EnumType.STRING)
    Move opponentMove;

    @Column(name = "game_status")
    @Enumerated(EnumType.STRING)
    Status gameStatus;

    public GameEntity(UUID gameId) {
        this.gameId = gameId;
    }

}