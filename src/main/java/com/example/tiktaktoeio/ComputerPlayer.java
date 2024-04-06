package com.example.tiktaktoeio;

public class ComputerPlayer {
    private final Game game;
    private final BaseStrategy strategy;

    public ComputerPlayer(Game game, BaseStrategy strategy) {
        this.game = game;
        this.strategy = strategy;
    }

    public int[] move() {
        if (game.isGameOver()) {
            return null;
        }

        return strategy.getCoordinate();
    }
}
