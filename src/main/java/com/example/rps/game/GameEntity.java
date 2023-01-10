package com.example.rps.game;

import com.example.rps.player.PlayerEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@Entity(name = "game")
@Table(name = "Game")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GameEntity {

    @Id
    @Column(name = "game_id")
    private UUID gameId;

    @OneToOne
    @JoinColumn(name = "playerOneGame")
    PlayerEntity playerOne;

    @Column(name = "player_move")
    @Enumerated(EnumType.STRING)  // enumerated gör så att det står med bokstäver istället för siffror
    Move playerMove;              // tex istället för 1 står det ROCK

    @OneToOne
    @JoinColumn(name = "playerTwoGame")
    PlayerEntity playerTwo;

    @Column(name = "opponent_move")
    @Enumerated(EnumType.STRING)
    Move opponentMove;

    @Column(name = "game_status")
    @Enumerated(EnumType.STRING)
    Status gameStatus;

}
