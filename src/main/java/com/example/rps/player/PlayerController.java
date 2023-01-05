package com.example.rps.player;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class PlayerController {

    PlayerService playerService;

    @GetMapping("/auth/token")
    public UUID getToken() {
        return playerService.getToken()
                .getPlayerId();
    }

/*    @PostMapping("/user/name")
    public void setName(@RequestBody UpdatePlayer updatePlayer, UUID playerId) {
        playerService.setName(updatePlayer, playerId);
    }*/

}
