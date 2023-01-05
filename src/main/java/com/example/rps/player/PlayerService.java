package com.example.rps.player;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PlayerService {

    PlayerRepository playerRepository;

    public PlayerEntity getToken() {
        PlayerEntity playerEntity = new PlayerEntity(
                UUID.randomUUID()
        );
        playerRepository.save(playerEntity);
        return playerEntity;
    }

/*    public void setName(UpdatePlayer updatePlayer, UUID playerId) {
        Optional<PlayerEntity> playerEntity = playerRepository.findById(playerId);

        if (playerEntity.isPresent()) {
            playerEntity.get().setName(updatePlayer.getName());

            playerRepository.save(playerEntity.get());
        }

    }*/
}
