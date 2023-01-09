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

    @PostMapping("start")
    public GameStatus startGame(@RequestHeader(value = "token") UUID playerId) {

        return gameService.startGame(playerId)
                .map(this::gameEntityToDTO)
                .orElse(null);
    }

    @PostMapping("join/{gameId}")
    public GameStatus joinGame(@RequestHeader(value = "token") UUID playerId,
                               @PathVariable("gameId") UUID gameId) {

        return gameService.joinGame(playerId, gameId)
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