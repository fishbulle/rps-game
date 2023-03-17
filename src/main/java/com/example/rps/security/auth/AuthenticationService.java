package com.example.rps.security.auth;

import com.example.rps.NotFoundException;
import com.example.rps.player.PlayerEntity;
import com.example.rps.player.PlayerRepository;
import com.example.rps.security.config.JwtService;
import com.example.rps.security.user.Role;
import com.example.rps.security.user.User;
import com.example.rps.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PlayerRepository playerRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        // builds and maps a player entity and a user entity

        var player = PlayerEntity
                .builder()
                .playerId(UUID.randomUUID())
                .username(request.getName())
                .build();

        var user = User
                .builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .playerEntity(player)
                .build();

        playerRepository.save(player);
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

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

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException("Not found."));

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
