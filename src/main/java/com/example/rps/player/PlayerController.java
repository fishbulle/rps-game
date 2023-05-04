package com.example.rps.player;

import com.example.rps.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/token")
    public UUID getToken() {
        return playerService.getToken()
                .getPlayerId();
    }

    @PostMapping("/create")
    public void setPlayerName(@RequestBody UpdatePlayer updatePlayer,
                              @RequestHeader(value = "playerId") UUID playerId) throws NotFoundException {
        playerService.setPlayerName(updatePlayer, playerId);
    }
}
