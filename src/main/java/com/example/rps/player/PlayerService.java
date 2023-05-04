package com.example.rps.player;

import com.example.rps.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerEntity getToken() {
        PlayerEntity playerEntity = new PlayerEntity(   // create a new player entity object with token (UUID)
                UUID.randomUUID()
        );
        playerRepository.save(playerEntity);
        return playerEntity;
    }

    public void setPlayerName(UpdatePlayer updatePlayer, UUID playerId) throws NotFoundException {
        Optional<PlayerEntity> playerEntity = playerRepository.findById(playerId);

        if (playerEntity.isPresent()) {                             // if player with ID (token) exists,
            playerEntity.get().setName(updatePlayer.name());        // set name for player entity object found by player ID
            playerRepository.save(playerEntity.get());              // save
        } else {
            throw new NotFoundException("Player not found.");
        }
    }
}

