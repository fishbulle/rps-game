package com.example.rps.game;

public enum Move {

    ROCK {
        @Override
        public boolean beats(Move move) {
            return (SCISSORS == move);
        }
    },

    PAPER {
        @Override
        public boolean beats(Move move) {
            return (ROCK == move);
        }
    },

    SCISSORS {
        @Override
        public boolean beats(Move move) {
            return (PAPER == move);
        }
    };

    public abstract boolean beats(Move move);

}

