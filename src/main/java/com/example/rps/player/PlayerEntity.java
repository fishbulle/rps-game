package com.example.rps.player;

import com.example.rps.game.GameEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Entity(name = "players")
@Table(name = "Players")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlayerEntity implements UserDetails {

    @Id
    @Column(name = "player_id")
    private UUID playerId;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "playerOne")  // mappar ihop GameEntity med PlayerEntity playerOne
    @JsonIgnore
    private GameEntity playerOneGame;

    @OneToOne(mappedBy = "playerTwo")
    @JsonIgnore
    private GameEntity playerTwoGame;

    public PlayerEntity(UUID playerId) {
        this.playerId = playerId;
    }


    /**
     * Autogenerated methods from UserDetails interface
     * **/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
