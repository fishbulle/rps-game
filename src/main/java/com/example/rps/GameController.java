package com.example.rps;

import com.example.rps.game.GameEntity;
import com.example.rps.game.GameStatus;
import com.example.rps.game.Move;
import com.example.rps.game.Status;
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

    @PostMapping("start")
    public GameEntity startGame(@RequestBody GameStatus gameStatus,
                                @RequestBody PlayerDTO playerDTO) {
        return gameService.startGame(
                gameStatus.getGameId(),
                gameStatus.getStatus(),
                playerDTO.getId(),
                playerDTO.getName()
        );
    }

}