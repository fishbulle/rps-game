package com.example.rps.game;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GameService {

    GameRepository gameRepository;

    public GameEntity startGame(Status status,
                                String name) {
        GameEntity gameEntity = new GameEntity(
                UUID.randomUUID().toString(),
                status,
                name
        );

        return gameRepository.save(gameEntity);
    }

}
