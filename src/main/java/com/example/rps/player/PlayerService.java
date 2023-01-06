package com.example.rps.player;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PlayerService {

    PlayerRepository playerRepository;

    public PlayerEntity getToken() {                    //create new PlayerEntity. Calls constructor with only UUID in PlayerEntity
        PlayerEntity playerEntity = new PlayerEntity(   // create a new player entity object with token (UUID)
                UUID.randomUUID()
        );
        playerRepository.save(playerEntity);            //save entity via repository
        return playerEntity;
    }

    public void setPlayerName(UpdatePlayer updatePlayer, UUID playerId) {
        Optional<PlayerEntity> playerEntity = playerRepository.findById(playerId);      //return playerEntity with UUID playerId if it exists

        if (playerEntity.isPresent()) {                             // if player with ID (token) exists,
            playerEntity.get().setName(updatePlayer.getName());     // set name for player entity object found by player ID

            playerRepository.save(playerEntity.get());              // save
        }

    }
}
