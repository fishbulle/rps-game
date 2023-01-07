package com.example.rps.game;

import com.example.rps.player.PlayerEntity;
import com.example.rps.player.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.example.rps.game.Status.*;

@Service
@AllArgsConstructor
public class GameService {

    GameRepository gameRepository;
    PlayerRepository playerRepository;



    public GameStatus startGame(UUID playerID, PlayerEntity playerEntity) {
        UUID uuid = UUID.randomUUID();
        GameStatus gameStatus = new GameStatus(
                uuid,
                playerRepository.findById(playerID).get(),
                null,
                null,
                null,
                OPEN);
//        GameEntity gameEntity = new GameEntity(UUID.randomUUID());
//        gameEntity.setGameStatus(gameStatus.getStatus());
//        gameEntity.setPlayerOne(gameStatus.getName());

        GameEntity gameEntity = new GameEntity();
        gameEntity.setGameId(UUID.randomUUID());
        gameEntity.setGameStatus(OPEN);
        gameEntity.setPlayerOne(playerEntity.getPlayerOne().getPlayerOne());
        gameEntity.setPlayerOne(playerRepository.findById(playerID).get());

        gameRepository.save(gameEntity);        //returnerades tidigare

        return gameStatus;
    }
/*
    public Optional<GameEntity> joinGame(UUID gameId, GameStatus gameStatus) {

        return gameRepository.findById(gameId)
                .map(gameEntity -> {
                    if (gameStatus.getOpponentName() == null)
                        gameEntity.setPlayerTwo(gameStatus.getOpponentName());
                    gameRepository.save(gameEntity);
                    return gameEntity;
                });

    }
*/


}
