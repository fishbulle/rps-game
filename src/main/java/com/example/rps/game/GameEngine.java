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

    public Result evaluateMove(Move playerOne, Move playerTwo) {

        Result result = Result.DRAW;

        if (playerOne.beats(playerTwo)) {
            result = Result.WIN;
        } else if (playerTwo.beats(playerOne)) {
            result = Result.LOSE;
        }

        return result;
    }
}
