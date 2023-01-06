package com.example.rps.game;

import com.example.rps.player.PlayerEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GameService {

    GameRepository gameRepository;

/*
    public GameEntity startGame(GameStatus gameStatus) {
        GameEntity gameEntity = new GameEntity(
                UUID.randomUUID(),
                null,
                null,
                null,
                null,
                Status.OPEN
        );

        return gameRepository.save(gameEntity);
    }

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
