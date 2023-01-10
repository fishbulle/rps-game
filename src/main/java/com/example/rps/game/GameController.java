package com.example.rps.game;

import com.example.rps.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class GameController {

    GameService gameService;

    @PostMapping("/start")
    public GameStatus startGame(@RequestHeader(value = "token") UUID playerId) {

        return gameService.startGame(playerId)
                .map(this::gameEntityToDTO)
                .orElse(null);
    }

    @PostMapping("/join/{gameId}")
    public GameStatus joinGame(@RequestHeader(value = "token") UUID playerId,
                               @PathVariable("gameId") UUID gameId) throws NotFoundException {

        return gameService.joinGame(playerId, gameId)
                .map(this::gameEntityToDTO)
                .orElse(null);
    }

    @GetMapping("/games")
    public List<GameEntity> getOpenGames() {
        return gameService.getOpenGames()
                .stream()
                .filter(games -> games.gameStatus.equals(Status.OPEN))  // filter games on status OPEN
                .collect(Collectors.toList());                          // collect them to a list
    }

    @GetMapping("/games/{gameId}")
    public GameStatus gameInfo(@PathVariable("gameId") UUID gameId) throws NotFoundException {
        return gameService.gameInfo(gameId)
                .map(this::gameEntityToDTO)
                .orElse(null);
    }

    @PostMapping("/games/move/{sign}")
    public GameStatus makeMove(@PathVariable("sign") String sign,
                               @RequestHeader(value = "token") UUID playerId,
                               @RequestBody GameStatus gameStatus) throws NotFoundException {
        return gameService.makeMove(sign, playerId, gameStatus)
                .map(this::gameEntityToDTO)
                .orElse(null);
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
