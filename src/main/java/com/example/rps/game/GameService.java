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


    public Optional<GameEntity> startGame(UUID playerId) {
        GameEntity gameEntity = new GameEntity(
                UUID.randomUUID(),
                playerRepository.findById(playerId).get(),
                null,
                null,
                null,
                OPEN
        );

        gameRepository.save(gameEntity);
        playerRepository.getReferenceById(playerId).setPlayerOneGame(gameEntity);

        return Optional.of(gameEntity);
    }

    public Optional<GameEntity> joinGame(UUID playerId, UUID gameId) {
        GameEntity gameEntity = new GameEntity();

        if (gameRepository.existsById(gameId)) {
            gameEntity = gameRepository.findById(gameId).get();

            gameEntity.setPlayerTwo(playerRepository.getReferenceById(playerId));
            gameEntity.setGameStatus(ACTIVE);

            gameRepository.save(gameEntity);
        }

        return Optional.of(gameEntity);
    }

/*    public GameEntity joinGame(UUID gameId, UUID playerId) throws NotFoundException {

        // här vill vi hitta ett spel via gameId
        // lägga in spelare 2 (namn & id), samt ändra status på spelet till ACTIVE

        Optional<GameEntity> gameEntity = gameRepository.findById(gameId);  // här hämtar vi spelet som startades i metoden ovan

        if (gameEntity.isPresent()) {
            gameEntity.get().setPlayerTwo(playerRepository.getReferenceById(playerId)); // hämtar id för spelare och sätter den som player2
            gameEntity.get().setGameStatus(ACTIVE);
        }
        else {
            throw new NotFoundException("Game not found");
        }

        gameRepository.save(gameEntity.get());

        return gameEntity.get();
    }*/
}
