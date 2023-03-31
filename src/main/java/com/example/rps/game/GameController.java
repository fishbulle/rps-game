package com.example.rps.game;

import com.example.rps.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@AllArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/start")
    public GameStatus startGame(@RequestHeader(value = "playerId") UUID playerId) {

        return gameService.startGame(playerId)
                .map(this::gameEntityToDTO)
                .orElse(null);
    }

    @PostMapping("/games/join/{gameId}")
    public GameStatus joinGame(@RequestHeader(value = "playerId") UUID playerId,
                               @PathVariable("gameId") UUID gameId) throws NotFoundException {

        return gameService.joinGame(playerId, gameId)
                .map(this::gameEntityToDTO)
                .orElse(null);
    }

    @GetMapping("/games")
    public List<GameEntity> getOpenGames() {
        return gameService.getOpenGames()
                .stream()
                .filter(games -> games.status.equals(Status.OPEN))  // filter games on status OPEN
                .collect(Collectors.toList());                          // collect them to a list
    }

    @GetMapping("/games/info")
    public GameStatus gameInfo(@RequestHeader(value = "gameId") UUID gameId) throws NotFoundException {
        return gameService.gameInfo(gameId)
                .map(this::gameEntityToDTO)
                .orElse(null);
    }

    // same as above method except it shows different status (win/lose)
    // depending on whether it's player1 or player2 checking
    @GetMapping("/games/result")
    public GameStatus gameResult(@RequestHeader(value = "gameId") UUID gameId,
                                 @RequestHeader(value = "playerId") UUID playerId) throws NotFoundException {
        return gameService.gameResult(gameId, playerId)
                .map(this::gameEntityToDTO)
                .orElse(null);
    }

    @PostMapping("/games/move/{sign}")
    public GameStatus makeMove(@PathVariable("sign") String sign,
                               @RequestHeader(value = "playerId") UUID playerId,
                               @RequestBody GameStatus gameStatus) throws NotFoundException {
        return gameService.makeMove(sign, playerId, gameStatus)
                .map(this::gameEntityToDTO)
                .orElse(null);
    }

    @DeleteMapping("games/delete")
    public GameStatus deleteGame(@RequestHeader(value = "gameId") UUID gameId) throws NotFoundException {
        return gameService.deleteGame(gameId)
                .map(this::gameEntityToDTO)
                .orElse(null);
    }

    private GameStatus gameEntityToDTO(GameEntity gameEntity) {

        return new GameStatus(
                gameEntity.getGameId(),
                gameEntity.getPlayerOne(),
                gameEntity.getPlayerOneMove(),
                gameEntity.getPlayerTwo(),
                gameEntity.getPlayerTwoMove(),
                gameEntity.getStatus(),
                gameEntity.getResult()
        );
    }
}
