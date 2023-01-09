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
        PlayerEntity playerEntity = playerRepository.findById(playerId);

        GameEntity gameEntity = new GameEntity(
                UUID.randomUUID(),
                playerEntity,
                null,
                null,
                null,
                OPEN
        );

        playerEntity.setPlayerOneGame(gameEntity);
        gameEntity.setPlayerOne(playerEntity);

        gameRepository.save(gameEntity);
        playerRepository.save(playerEntity);

        return gameEntity;
    }

    public GameStatus joinGame(UUID gameId,
                               UUID playerId,
                               PlayerEntity playerEntity) throws NotFoundException {
       // PlayerEntity playerEntity = new PlayerEntity();     //this doesn't work, but I have had a bottle of wine, I coded anyway, but am calling it for today

        // här vill vi hitta ett spel via gameId
        // lägga in spelare 2 (namn & id), samt ändra status på spelet till ACTIVE

        Optional<GameEntity> gameEntity = gameRepository.findById(gameId);  // här hämtar vi spelet som startades i metoden ovan

        if (gameEntity.isPresent()) {
            gameEntity.get().setPlayerTwo(playerRepository.findById(playerId).get()); // hämtar id för spelare 2
            gameEntity.get().setPlayerTwo(playerEntity.getPlayerTwoGame().getPlayerTwo()); // här är nåt fel
            gameEntity.get().setGameStatus(ACTIVE);
        }
        else {
            throw new NotFoundException("Game not found");
        }

        GameStatus gameStatus = new GameStatus(
                gameId,
                playerEntity.getPlayerOneGame().getPlayerOne(),
                null,
                playerEntity.getPlayerTwoGame().getPlayerTwo(),
                null,
                ACTIVE
        );

        return gameStatus;
    }

    /*    public GameStatus startGame(UUID playerId) {
        UUID uuid = UUID.randomUUID();
        GameStatus gameStatus = new GameStatus(
                uuid,
                playerRepository.findById(playerId).get(),
                null,
                null,
                null,
                OPEN);

        GameEntity gameEntity = new GameEntity();
        gameEntity.setGameId(UUID.randomUUID());
        gameEntity.setGameStatus(OPEN);
        gameEntity.setPlayerOne(playerEntity.getPlayerOneGame().getPlayerOne());
        gameEntity.setPlayerOne(playerRepository.findById(playerId).get());

        gameRepository.save(gameEntity);        // returnerades tidigare

        return gameStatus;
    }*/
}
