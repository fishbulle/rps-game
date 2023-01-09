package com.example.rps.game;

import com.example.rps.NotFoundException;
import com.example.rps.player.PlayerEntity;
import com.example.rps.player.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class GameController {

    GameService gameService;
    PlayerRepository playerRepository;


    @PostMapping("/start")
    public GameStatus startGame(@RequestHeader(value = "token") UUID playerId) {

        return gameEntityToDTO(
                gameService.startGame(playerId)
        );
    }

    @PostMapping("/join/{gameId}")
    public GameStatus joinGame(@PathVariable("gameId") UUID gameId,
                               @RequestHeader(value = "token") UUID playerId,
                               PlayerEntity playerEntity) throws NotFoundException {

        return gameEntityToDTO(
                gameService.joinGame(gameId, playerId, playerEntity)
        );
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