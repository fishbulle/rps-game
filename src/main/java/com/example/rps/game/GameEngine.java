package com.example.rps.game;

import org.springframework.stereotype.Component;

@Component
public class GameEngine {

    public Status evaluateMove(Move playerOne, Move playerTwo) {

        Status result = Status.DRAW;

        if (playerOne.beats(playerTwo)) {
            result = Status.WIN;
        }

        else if (playerTwo.beats(playerOne)) {
            result = Status.LOSE;
        }

        return result;
    }
}
