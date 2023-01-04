package com.example.rps;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerEntity {

    @Id
    private String id = UUID.randomUUID().toString();  // deklarera UUID.randomUUID redan här eller i serviceklass?
    private String name;
    Move move;
    private String opponentName;
    Move opponentMove;

}
