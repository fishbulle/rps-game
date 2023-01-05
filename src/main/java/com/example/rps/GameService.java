package com.example.rps;

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

/*    public PlayerEntity getToken() {
        PlayerEntity playerEntity = new PlayerEntity(
                UUID.randomUUID().toString());
        return playerRepository.save(playerEntity);
    }*/


    public PlayerEntity setName(String name) {
/*        PlayerEntity playerEntity = playerRepository.findById(id)
                .orElseThrow(RuntimeException::new);*/
        PlayerEntity playerEntity = new PlayerEntity(
                UUID.randomUUID().toString(),
                name
        );
        return playerRepository.save(playerEntity);
    }

    public GameEntity startGame(String gameId, Status status, String playerId, String playerName ) {
        GameEntity gameEntity = new GameEntity(
                UUID.randomUUID().toString(),
                status,
                playerId,
                playerName
        );
        return gameRepository.save(gameEntity);
    }
}
