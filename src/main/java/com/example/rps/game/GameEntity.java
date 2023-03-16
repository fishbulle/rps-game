package com.example.rps.game;

import com.example.rps.player.PlayerEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GameEntity {

    @Id
    @Column(name = "game_id")
    UUID gameId;

    @ManyToOne
    @JoinColumn(name = "player1_id")
    PlayerEntity playerOne;

    @Column(name = "player_one_move")
    @Enumerated(EnumType.STRING)
    Move playerOneMove;

/*    @Column(name = "player_one_score")
    int playerOneScore;*/

    @ManyToOne
    @JoinColumn(name = "player2_id")
    PlayerEntity playerTwo;

    @Column(name = "player_two_move")
    @Enumerated(EnumType.STRING)
    Move playerTwoMove;

/*    @Column(name = "player_two_score")
    int playerTwoScore;*/

    @Column(name = "game_status")
    @Enumerated(EnumType.STRING)
    Status status;

    @Column(name = "game_result")
    @Enumerated(EnumType.STRING)
    Result result;
}
