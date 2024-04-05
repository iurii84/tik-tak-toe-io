package com.example.tiktaktoeio;

import java.util.Arrays;
import java.util.List;

public class ComputerPlayer {
    private final Game game;
    private final BaseStrategy strategy;

    private WinningVector currentVector;

    public ComputerPlayer(Game game, BaseStrategy strategy) {
        this.game = game;
        this.strategy = strategy;
    }

    public int[] move() {
        if (game.isGameOver()) {
            return null;
        }

        WinningVector selectedVector = strategy.selectVector();
        if (selectedVector != null) {
            List<int[]> coordinates = selectedVector.getVector();
            int index = strategy.selectVectorIndex(selectedVector);
            int[] coordinate = coordinates.get(index);

            boolean moveAction = this.game.move(coordinate[0], coordinate[1]);
            if(moveAction) {
                return coordinate;
            }
            return null;
        }
        else {
            int[] coordinate = strategy.findFirstAvailableCell();
            System.out.println(STR."NO MORE VECTORS FOUND: \{Arrays.toString(coordinate)}");
            boolean moveAction = this.game.move(coordinate[0], coordinate[1]);
            if(moveAction) {
                return coordinate;
            }
        }
        return null;
    }
}
