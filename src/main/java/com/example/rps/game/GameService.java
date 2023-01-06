package com.example.rps.game;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GameService {

    GameRepository gameRepository;

    public GameEntity startGame(GameStatus gameStatus) {
        GameEntity gameEntity = new GameEntity(
                UUID.randomUUID(),
                gameStatus.getStatus(),
                gameStatus.getName(),
                gameStatus.getOpponentName()
        );

        return gameRepository.save(gameEntity);
    }

    public GameEntity joinGame(GameStatus gameStatus) {

        Optional<GameEntity> gameEntity = gameRepository.findById(gameStatus.getGameId());

        if (gameEntity.isPresent()) {
            gameEntity.get().setStatus(gameStatus.getStatus()),
            gameEntity.get().setName(gameStatus.getName()),
            gameEntity.get().setOpponentName(gameStatus.getOpponentName());
        }

       return gameRepository.save(gameEntity.get());

    }


}
