package com.example.rps.game;

import com.example.rps.game.GameEntity;
import com.example.rps.game.GameRepository;
import com.example.rps.game.Status;
import com.example.rps.player.PlayerEntity;
import com.example.rps.player.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GameService {

    GameRepository gameRepository;

    public GameEntity startGame(String gameId,
                                Status status) {
        GameEntity gameEntity = new GameEntity(
                UUID.randomUUID().toString(),
                status
        );

        return gameRepository.save(gameEntity);
    }

}
