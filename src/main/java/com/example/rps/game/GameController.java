package com.example.rps.game;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class GameController {

    GameService gameService;

    @PostMapping("/start")
    public GameEntity startGame(@RequestBody GameStatus gameStatus) {
        return gameService.startGame(gameStatus);
    }

    @GetMapping("/games/join/{gameId}")
    public GameEntity joinGame(@PathVariable("gameId")
                               @RequestBody GameStatus gameStatus) {
        return gameService.joinGame(gameStatus);
    }

}