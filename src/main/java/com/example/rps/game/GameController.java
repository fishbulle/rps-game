package com.example.rps.game;

import com.example.rps.NotFoundException;
import com.example.rps.player.PlayerEntity;
import com.example.rps.player.PlayerRepository;
import com.example.rps.player.UpdatePlayer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class GameController {

    GameService gameService;
    PlayerRepository playerRepository;


    @PostMapping("/start")
    public GameStatus startGame(@RequestHeader(value = "token") UUID playerId) {
        PlayerEntity playerEntity = new PlayerEntity(
                playerId,
                playerRepository.findById(playerId).toString(),
                new GameEntity());

        return gameService.startGame(playerId, playerEntity);
    }

    @PostMapping("/join")
    public GameStatus joinGame(@RequestHeader(value = "token") UUID gameId,
                               UUID playerId,
                               UpdatePlayer updatePlayer) throws NotFoundException {

        return gameService.joinGame(gameId, playerId, updatePlayer);
    }


/*    @GetMapping("/games/join/{gameId}")
    public Optional<GameEntity> joinGame(@PathVariable("gameId") UUID gameId,
                             @RequestBody GameStatus gameStatus) {
        return gameService.joinGame(gameId, gameStatus);
    }*/

}