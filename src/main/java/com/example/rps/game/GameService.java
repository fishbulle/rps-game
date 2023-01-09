package com.example.rps.game;

import com.example.rps.NotFoundException;
import com.example.rps.player.PlayerEntity;
import com.example.rps.player.PlayerRepository;
import com.example.rps.player.UpdatePlayer;
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


    public GameEntity startGame(UUID playerId) {
        Optional<PlayerEntity> playerEntity = playerRepository.findById(playerId);

        GameEntity gameEntity = new GameEntity(
                UUID.randomUUID(),
                playerEntity.get(),
                null,
                null,
                null,
                OPEN
        );

        /*playerEntity.get().setPlayerOneGame(gameEntity);
        gameEntity.setPlayerOne(playerEntity.get());*/

        gameRepository.save(gameEntity);
        playerRepository.save(playerEntity.get());

        return gameEntity;
    }

    public GameEntity joinGame(UUID gameId,
                               UUID playerId,
                               PlayerEntity playerEntity) throws NotFoundException {

        // här vill vi hitta ett spel via gameId
        // lägga in spelare 2 (namn & id), samt ändra status på spelet till ACTIVE

        Optional<GameEntity> gameEntity = gameRepository.findById(gameId);  // här hämtar vi spelet som startades i metoden ovan

        if (gameEntity.isPresent()) {
            gameEntity.get().setPlayerTwo(playerRepository.findById(playerId).get()); // hämtar id för spelare och sätter den som player2
            gameEntity.get().setPlayerTwo(playerEntity.getPlayerTwoGame().getPlayerTwo()); // här är nåt fel !!!! se nedan
            gameEntity.get().setGameStatus(ACTIVE);
        }
        else {
            throw new NotFoundException("Game not found");
        }

        gameRepository.save(gameEntity.get());
        playerRepository.save(playerEntity);

        return gameEntity.get();

        /* Cannot invoke "com.example.rps.game.GameEntity.getPlayerTwo()"
        because the return value of "com.example.rps.player.PlayerEntity.getPlayerTwoGame()" is null */
    }
}
