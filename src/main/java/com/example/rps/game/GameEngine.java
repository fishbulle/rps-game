package com.example.rps.game;

import org.springframework.stereotype.Component;

/**
 * GameEngine that handles the result of the game
 * **/

@Component
public class GameEngine {

    /**
     * Takes playerOne Move (enum) and playerTwo Move and
     * evaluates the result (from playerOne's perspective)
     * **/

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
