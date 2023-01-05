package com.example.rps;

import com.example.rps.player.PlayerDTO;
import com.example.rps.player.PlayerEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/games")
public class GameController {

    GameService gameService;

/*    @GetMapping("auth/token")
    public PlayerEntity getToken() {
        return gameService.getToken();
    }*/

    @PostMapping("user/name")
    public PlayerEntity setName(@RequestBody PlayerDTO playerDTO) {
        return gameService.setName(
                playerDTO.getName()
        );
    }

}