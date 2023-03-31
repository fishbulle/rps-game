package com.example.rps.player;

import com.example.rps.game.GameEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name = "players")
@Table(name = "Players")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlayerEntity implements UserDetails {

    @Id
    @Column(name = "player_id")
    private UUID playerId;

    @Column(name = "name")
    private String name;

    @Column(name = "username",
            unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "playerOne")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<GameEntity> playerOneGame;

    @OneToMany(mappedBy = "playerTwo")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<GameEntity> playerTwoGame;

    public PlayerEntity(UUID playerId) {
        this.playerId = playerId;
    }

    public void setPlayerOneGame(GameEntity playerOneGame) {
        this.playerOneGame.add(playerOneGame);
    }

    public void setPlayerTwoGame(GameEntity playerTwoGame) {
        this.playerOneGame.add(playerTwoGame);
    }

    @Override  // returns a list of roles
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}