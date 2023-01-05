package com.example.rps;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "players")
@Getter
@Setter
@NoArgsConstructor
@Table(name = "players")
public class PlayerEntity {

    @Id
    private String id;  // deklarera UUID.randomUUID redan här eller i serviceklass?
    private String name;
    private String opponentName;

    public PlayerEntity(String id) {
        this.id = id;
    }

    public PlayerEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
