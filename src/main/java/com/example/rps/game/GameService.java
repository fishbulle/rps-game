package com.example.rps.game;

import com.example.rps.NotFoundException;
import com.example.rps.player.PlayerEntity;
import com.example.rps.player.PlayerRepository;
import com.example.rps.player.UpdatePlayer;
import lombok.AllArgsConstructor;
import org.springframework.cache.interceptor.AbstractCacheInvoker;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.example.rps.game.Status.*;

@Service
@AllArgsConstructor
public class GameService {

    GameRepository gameRepository;
    PlayerRepository playerRepository;
//    PlayerEntity playerEntity;



    public GameStatus startGame(UUID playerId, PlayerEntity playerEntity) {
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

        gameRepository.save(gameEntity);        //returnerades tidigare

        return gameStatus;
    }

    public GameStatus joinGame(UUID gameId, UUID playerId) throws NotFoundException {
        PlayerEntity playerEntity = new PlayerEntity();     //this doesn't work, but I have had a bottle of wine, I coded anyway, but am calling it for today

        // här vill vi hitta ett spel via gameId
        // lägga in spelare 2 (namn & id), samt ändra status på spelet till ACTIVE

        Optional<GameEntity> gameEntity = gameRepository.findById(gameId);  // här hämtar vi spelet som startades i metoden ovan

        if (gameEntity.isPresent()) {
            gameEntity.get().setGameStatus(ACTIVE);
            gameEntity.get().setPlayerTwo(playerEntity.getPlayerTwoGame().getPlayerTwo());
            gameEntity.get().setPlayerTwo(playerRepository.findById(playerId).get());
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
