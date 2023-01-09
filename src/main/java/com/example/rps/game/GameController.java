package com.example.rps.game;

import com.example.rps.NotFoundException;
import com.example.rps.player.PlayerEntity;
import com.example.rps.player.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.rps.game.Status.OPEN;

@RestController
@AllArgsConstructor
public class GameController {

    GameService gameService;
    PlayerRepository playerRepository;
    private final GameRepository gameRepository;


    @PostMapping("/start")
    public GameStatus startGame(@RequestHeader(value = "token") UUID playerId) {

        return gameService.startGame(playerId)
                .map(this::gameEntityToDTO)
                .orElse(null);
    }

    @PostMapping("/join/{gameId}")
    public GameStatus joinGame(@PathVariable("gameId") UUID gameId,
                               @RequestHeader(value = "token") UUID playerId,
                               PlayerEntity playerEntity) throws NotFoundException {

        return gameService.joinGame(gameId, playerId, playerEntity);
    }


/*    @GetMapping("/games/join/{gameId}")
    public Optional<GameEntity> joinGame(@PathVariable("gameId") UUID gameId,
                             @RequestBody GameStatus gameStatus) {
        return gameService.joinGame(gameId, gameStatus);
    }*/

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