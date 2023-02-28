package com.example.rps.player;

import com.example.rps.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/auth/token")
    public UUID getToken() {
        return playerService.getToken()
                .getPlayerId();
    }

    @CrossOrigin
    @PostMapping("/user/name")
    public void setPlayerName(@RequestBody UpdatePlayer updatePlayer,
                              @RequestHeader(value = "token") UUID playerId) throws NotFoundException {
        playerService.setPlayerName(updatePlayer, playerId);
    }
}
