package com.example.rps.game;

import com.example.rps.NotFoundException;
import com.example.rps.player.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.rps.game.Result.*;
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
                OPEN,
                null
        );

        gameRepository.save(gameEntity);
        playerRepository.getReferenceById(playerId).setPlayerOneGame(gameEntity);

        return Optional.of(gameEntity);
    }

    public Optional<GameEntity> joinGame(UUID playerId, UUID gameId) throws NotFoundException {
        GameEntity gameEntity;

        // lägga till if (spelet is ACTIVE) sout "Game is full" ??

        if (gameRepository.existsById(gameId) ) {
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
                if (gameEntity.playerOne.getMove().beats(gameEntity.playerTwo.getMove())) {
                    gameEntity.setGameResult(WIN);
                } else if (gameEntity.playerTwo.getMove().beats(gameEntity.playerOne.getMove())) {
                    gameEntity.setGameResult(LOSE);
                } else {
                    gameEntity.setGameResult(DRAW);
                }
            }
            if (gameEntity.playerTwo.getPlayerId().equals(playerId)) {
                if (gameEntity.playerTwo.getMove().beats(gameEntity.playerOne.getMove())) {
                    gameEntity.setGameResult(WIN);
                } else if (gameEntity.playerOne.getMove().beats(gameEntity.playerTwo.getMove())) {
                    gameEntity.setGameResult(LOSE);
                } else {
                    gameEntity.setGameResult(DRAW);
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
                    case "rock" -> gameEntity.playerOne.setMove(Move.ROCK);
                    case "paper" -> gameEntity.playerOne.setMove(Move.PAPER);
                    case "scissors" -> gameEntity.playerOne.setMove(Move.SCISSORS);
                }
            }
            if (gameEntity.playerTwo.getPlayerId().equals(playerId)) {
                switch (sign) {
                    case "rock" -> gameEntity.playerTwo.setMove(Move.ROCK);
                    case "paper" -> gameEntity.playerTwo.setMove(Move.PAPER);
                    case "scissors" -> gameEntity.playerTwo.setMove(Move.SCISSORS);
                }
            }
        } else {
            throw new NotFoundException("Game not found.");
        }

        if (gameEntity.playerOne.getMove() != null
                && gameEntity.playerTwo.getMove() != null) {
            Result result = gameEngine.evaluateMove(gameEntity.playerOne.getMove(), gameEntity.playerTwo.getMove());
            gameEntity.setGameResult(result);
            gameEntity.setGameStatus(FINISHED);

            System.out.println(gameEntity.playerOne.getName() + " " + result + "!");
        }

        gameRepository.save(gameEntity);

        return Optional.of(gameEntity);
    }
}
