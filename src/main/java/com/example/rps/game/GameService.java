package com.example.rps.game;

import com.example.rps.player.PlayerEntity;
import com.example.rps.player.PlayerRepository;
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
        gameEntity.setPlayerOne(playerEntity.getPlayerOne().getPlayerOne());
        gameEntity.setPlayerOne(playerRepository.findById(playerId).get());

        gameRepository.save(gameEntity);        //returnerades tidigare

        return gameStatus;
    }

    public GameStatus joinGame(UUID gameId, UUID playerId) {
        PlayerEntity playerEntity = new PlayerEntity();     //this doesn't work, but I have had a bottle of wine, I coded anyway, but am calling it for today

        // här vill vi hitta ett spel via gameId, visa spelaren som redan är ansluten,
        // lägga in spelare 2 (namn & id), samt ändra status på spelet till ACTIVE

        gameRepository.getReferenceById(gameId);  // här hämtar vi spelet som startades i metoden ovan

        // if gameId exists .. bla bla
        // else  bla bla

        GameStatus gameStatus = new GameStatus(
                gameId,
                playerEntity.getPlayerOne().getPlayerOne(),
                null,
                playerEntity.getPlayerTwo().getPlayerTwo(),
                null,
                ACTIVE
        );

        GameEntity gameEntity = new GameEntity();
        gameEntity.setGameStatus(ACTIVE);
        gameEntity.setPlayerTwo(playerEntity.getPlayerTwo().getPlayerTwo());
        gameEntity.setPlayerTwo(playerRepository.findById(playerId).get());

        gameRepository.save(gameEntity);

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
