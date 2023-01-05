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

/*    public void setName(PlayerDTO playerDTO, UUID playerId) {
        Optional<PlayerEntity> playerEntity = playerRepository.findBy(playerId);

        if (playerEntity.isPresent()) {
            playerEntity.get().setName(playerDTO.getName());

            playerRepository.save(playerEntity.get());
        }

    }*/
}
