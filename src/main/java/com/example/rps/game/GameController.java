package com.example.rps.game;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class GameController {

    GameService gameService;

    @PostMapping("/start")
    public GameEntity startGame(@RequestBody GameStatus gameStatus) {
        return gameService.startGame(gameStatus);
    }

/*    @GetMapping("/games/join/{gameId}")
    public Optional<GameEntity> joinGame(@PathVariable("gameId") UUID gameId,
                             @RequestBody GameStatus gameStatus) {
        return gameService.joinGame(gameId, gameStatus);
    }*/

}