package com.example.rps.game;

import com.example.rps.NotFoundException;
import com.example.rps.player.PlayerEntity;
import com.example.rps.player.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.http.HttpHeaders;
import java.util.List;
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

    public Optional<GameEntity> joinGame(UUID playerId, UUID gameId) throws NotFoundException {
        GameEntity gameEntity;

        if (gameRepository.existsById(gameId)) {
            gameEntity = gameRepository.findById(gameId).get();

            gameEntity.setPlayerTwo(playerRepository.getReferenceById(playerId));
            gameEntity.setGameStatus(ACTIVE);

            gameRepository.save(gameEntity);
        } else {
            throw new NotFoundException("Something went wrong.");
        }

        playerRepository.getReferenceById(playerId).setPlayerTwoGame(gameEntity);

        return Optional.of(gameEntity);
    }

    public List<GameEntity> getOpenGames() {
        return gameRepository.findAll();
    }

    public Optional<GameEntity> gameInfo(UUID gameId) throws NotFoundException {

        GameEntity gameEntity;

        if (gameRepository.existsById(gameId)) {
            gameEntity = gameRepository.findById(gameId).get();
        } else {
            throw new NotFoundException("Game not found.");
        }

        return Optional.of(gameEntity);
    }

    public Optional<GameEntity> makeMove(String sign,
                                         UUID playerId,
                                         UUID gameId) throws NotFoundException {
        GameEntity gameEntity;

        if (gameRepository.existsById(gameId)) {
            gameEntity = gameRepository.findById(gameId).get();
            if (playerRepository.existsById(playerId)) {
                if (playerRepository.getReferenceById(playerId).equals(gameEntity.getPlayerOne())) {
                    gameEntity.setPlayerMove(Move.valueOf(sign));
                } else {
                    gameEntity.setOpponentMove(Move.valueOf(sign));
                }
            }
        } else {
            throw new NotFoundException("Game not found.");
        }

        return Optional.of(gameEntity);
    }
}
