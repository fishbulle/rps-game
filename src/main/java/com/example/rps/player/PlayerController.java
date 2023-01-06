package com.example.rps.player;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class PlayerController {

    PlayerService playerService;

    @GetMapping("/auth/token")
    public UUID getToken() {            //playerService.getToken returns PlayerEntity. Here we get id from the entity, and return it.
        return playerService.getToken()
                .getPlayerId();
    }

    @PostMapping("/user/name")
    public void setPlayerName(@RequestBody UpdatePlayer updatePlayer,
                              @RequestHeader(value = "token") UUID playerId) {  // playerService.setPlayerName returns void.
        playerService.setPlayerName(updatePlayer, playerId);                    // Send UUID playerId and UpdatePlayer, which contains only String name
    }

}
