package com.example.rps.security.auth;

import com.example.rps.NotFoundException;
import com.example.rps.player.PlayerEntity;
import com.example.rps.player.PlayerRepository;
import com.example.rps.security.config.JwtService;
import com.example.rps.player.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final PlayerRepository playerRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var player = PlayerEntity
                .builder()
                .playerId(UUID.randomUUID())
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        playerRepository.save(player);

        var jwtToken = jwtService.generateToken(player);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws NotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        var player = playerRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException("Not found."));

        var jwtToken = jwtService.generateToken(player);
        var playerId = player.getPlayerId();

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .playerId(playerId)
                .build();
    }
}