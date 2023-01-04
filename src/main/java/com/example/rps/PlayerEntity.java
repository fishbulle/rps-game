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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "players")
public class PlayerEntity {

    @Id
    private String id = UUID.randomUUID().toString();  // deklarera UUID.randomUUID redan här eller i serviceklass?
    private String name;
    Move move;
    private String opponentName;
    Move opponentMove;

}
