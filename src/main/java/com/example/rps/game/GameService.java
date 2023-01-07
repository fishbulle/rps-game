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


    public GameEntity startGame(UUID playerID, PlayerEntity playerEntity) {

//        GameEntity gameEntity = new GameEntity(UUID.randomUUID());
//        gameEntity.setGameStatus(gameStatus.getStatus());
//        gameEntity.setPlayerOne(gameStatus.getName());

        GameEntity gameEntity = new GameEntity();
        gameEntity.setGameId(UUID.randomUUID());
        gameEntity.setGameStatus(OPEN);
        gameEntity.setPlayerOne(playerEntity.getPlayerOne().getPlayerOne());
        gameEntity.setPlayerOne(playerRepository.findById(playerID).get());


        return gameRepository.save(gameEntity);
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
