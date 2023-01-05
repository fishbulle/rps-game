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
    PlayerRepository playerRepository;

/*    public GameEntity startGame(String gameId,
                                Status status,
                                String name,
                                String opponentName) {
        GameEntity gameEntity = new GameEntity(
                UUID.randomUUID().toString(),
                status,
                name,
                opponentName
        );

        return gameRepository.save(gameEntity);
    }*/

}
