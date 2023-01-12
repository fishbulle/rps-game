package com.example.rps.game;

import com.example.rps.NotFoundException;
import com.example.rps.player.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.rps.game.Status.*;

@Service
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final GameEngine gameEngine;

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
            throw new NotFoundException("Game not found.");
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

    public Optional<GameEntity> gameResult(UUID gameId, UUID playerId) throws NotFoundException {
        GameEntity gameEntity;

        // output changes depending on who is checking (player 1 vs player 2)

        if (gameRepository.existsById(gameId)) {
            gameEntity = gameRepository.findById(gameId).get();
            if (gameEntity.playerOne.getPlayerId().equals(playerId)) {
                if (gameEntity.getPlayerMove().beats(gameEntity.getOpponentMove())) {
                    gameEntity.setGameStatus(WIN);
                } else if (gameEntity.getOpponentMove().beats(gameEntity.getPlayerMove())) {
                    gameEntity.setGameStatus(LOSE);
                } else {
                    gameEntity.setGameStatus(DRAW);
                }
            }
            if (gameEntity.playerTwo.getPlayerId().equals(playerId)) {
                if (gameEntity.getOpponentMove().beats(gameEntity.getPlayerMove())) {
                    gameEntity.setGameStatus(WIN);
                } else if (gameEntity.getPlayerMove().beats(gameEntity.getOpponentMove())) {
                    gameEntity.setGameStatus(LOSE);
                } else {
                    gameEntity.setGameStatus(DRAW);
                }
            }
        } else {
            throw new NotFoundException("Game not found.");
        }

        gameRepository.save(gameEntity);

        return Optional.of(gameEntity);
    }

    public Optional<GameEntity> makeMove(String sign,
                                         UUID playerId,
                                         GameStatus gameStatus) throws NotFoundException {
        GameEntity gameEntity;

        if (gameRepository.existsById(gameStatus.gameId())) {
            gameEntity = gameRepository.findById(gameStatus.gameId()).get();
            if (gameEntity.playerOne.getPlayerId().equals(playerId)) {
                switch (sign) {
                    case "rock" -> gameEntity.setPlayerMove(Move.ROCK);
                    case "paper" -> gameEntity.setPlayerMove(Move.PAPER);
                    case "scissors" -> gameEntity.setPlayerMove(Move.SCISSORS);
                }
            }
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

        if (gameEntity.getOpponentMove() != null
                && gameEntity.getPlayerMove() != null) {
            Status result = gameEngine.evaluateMove(gameEntity.getPlayerMove(), gameEntity.getOpponentMove());
            gameEntity.setGameStatus(result);
        }

        gameRepository.save(gameEntity);

        return Optional.of(gameEntity);
    }
}
