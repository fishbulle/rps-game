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
    GameEngine gameEngine;


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
                                         GameStatus gameStatus) throws NotFoundException {
        GameEntity gameEntity;

        if (gameRepository.existsById(gameStatus.gameId())) {
            gameEntity = gameRepository.findById(gameStatus.gameId()).get();
            //if (playerRepository.existsById(playerId)) {
            if (gameEntity.playerOne.getPlayerId().equals(playerId)) {
                switch (sign) {
                    case "rock" -> gameEntity.setPlayerMove(Move.ROCK);
                    case "paper" -> gameEntity.setPlayerMove(Move.PAPER);
                    case "scissors" -> gameEntity.setPlayerMove(Move.SCISSORS);
                }
            }
            // if (playerRepository.getReferenceById(playerId).equals(gameEntity.getPlayerTwo()))
            if (gameEntity.playerTwo.getPlayerId().equals(playerId)) {
                switch (sign) {
                    case "rock" -> gameEntity.setOpponentMove(Move.ROCK);
                    case "paper" -> gameEntity.setOpponentMove(Move.PAPER);
                    case "scissors" -> gameEntity.setOpponentMove(Move.SCISSORS);
                }
            }
        } else {
            throw new NotFoundException("Game not found.");
        }

        Status result = gameEngine.evaluteMove(gameEntity.getPlayerMove(), gameEntity.getOpponentMove());
        gameEntity.setGameStatus(result);

        gameRepository.save(gameEntity);

        return Optional.of(gameEntity);
    }
}
