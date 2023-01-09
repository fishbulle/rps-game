package com.example.rps.game;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/start")
    public GameStatus startGame(@RequestHeader(value = "token") UUID playerId) {

        return gameService.startGame(playerId)
                .map(this::gameEntityToDTO)
                .orElse(null);
    }

    @PostMapping("/join/{gameId}")
    public GameStatus joinGame(@RequestHeader(value = "token") UUID playerId,
                               @PathVariable("gameId") UUID gameId) {

        return gameService.joinGame(playerId, gameId)
                .map(this::gameEntityToDTO)
                .orElse(null);
    }

    @GetMapping("/games")
    public List<GameEntity> getOpenGames() {
        return gameService.getOpenGames()
                .stream()
                .filter(games -> games.gameStatus.equals(Status.OPEN))
                .collect(Collectors.toList());
    }

    private GameStatus gameEntityToDTO(GameEntity gameEntity) {

        return new GameStatus(
                gameEntity.getGameId(),
                gameEntity.getPlayerOne(),
                gameEntity.getPlayerMove(),
                gameEntity.getPlayerTwo(),
                gameEntity.getOpponentMove(),
                gameEntity.getGameStatus()
                );
    }

}