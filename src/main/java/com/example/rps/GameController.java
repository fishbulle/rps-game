package com.example.rps;

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