package com.example.rps;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("api/games")
public class GameController {

    @GetMapping("/auth/token")
    public String getToken(String id) {
        
    }


}
