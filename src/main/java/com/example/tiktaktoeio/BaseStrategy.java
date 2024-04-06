package com.example.tiktaktoeio;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseStrategy {
    public final Game game;
    private final List<WinningVector> winningVectorPool;

    public BaseStrategy(Game game) {
        this.game = game;
        this.winningVectorPool = new ArrayList<>();
    }

    abstract public int[] getCoordinate();

    // Find available vectors where player can make a move to win
    public List<WinningVector> findAvailableVectors() {
        char opponentPlayer = game.getOpponentPlayer();
        char[][] gameField = game.getBoard();

        List<WinningVector> availableVectors = new ArrayList<>();

        int rowsCount = gameField.length;
        int colsCount = gameField[0].length;

        // Check horizontal and vertical lines
        for (int i = 0; i < rowsCount; i++) {
            WinningVector horizontalVector = new WinningVector();
            WinningVector verticalVector = new WinningVector();
            boolean freeHorizontalLine = true;
            boolean freeVerticalLine = true;

            for (int j = 0; j < colsCount; j++) {
                if (gameField[i][j] == opponentPlayer) {
                    freeHorizontalLine = false;
                } else {
                    horizontalVector.setCoordinate(new int[]{i, j});
                }

                if (gameField[j][i] == opponentPlayer) {
                    freeVerticalLine = false;
                } else {
                    verticalVector.setCoordinate(new int[]{j, i});
                }
            }

            if (freeHorizontalLine) {
                availableVectors.add(horizontalVector);
            }
            if (freeVerticalLine) {
                availableVectors.add(verticalVector);
            }
        }

        // Check main diagonal
        WinningVector mainDiagonalVector = new WinningVector();
        boolean freeMainDiagonal = true;
        for (int i = 0; i < colsCount; i++) {
            if (gameField[i][i] == opponentPlayer) {
                freeMainDiagonal = false;

                break;
            }
            mainDiagonalVector.setCoordinate(new int[]{i, i});
        }
        if (freeMainDiagonal) {
            availableVectors.add(mainDiagonalVector);
        }

        // Check anti-diagonal
        WinningVector antiDiagonalVector = new WinningVector();
        boolean freeAntiDiagonal = true;
        for (int i = 0; i < colsCount; i++) {
            if (gameField[i][colsCount - 1 - i] == opponentPlayer) {
                freeAntiDiagonal = false;

                break;
            }
            antiDiagonalVector.setCoordinate(new int[]{i, colsCount - 1 - i});
        }
        if (freeAntiDiagonal) {
            availableVectors.add(antiDiagonalVector);
        }

        return availableVectors;
    }

    // Find the first available cell to make a move
    public int[] findFirstAvailableCell() {
        char[][] board = game.getBoard();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Winner.EMPTY_CELL) {

                    return new int[]{i, j};
                }
            }
        }

        return null; // No available cell
    }

    // Add a winning vector to the pool
    public void addWinningVector(WinningVector winningVector) {
        this.winningVectorPool.add(winningVector);
    }

    // Get a winning vector from the pool
    public WinningVector getWinningVector(WinningVector existingWinningVector) {
        if (existingWinningVector != null && winningVectorPool.contains(existingWinningVector)) {

            return winningVectorPool.get(winningVectorPool.indexOf(existingWinningVector));
        } else {
            addWinningVector(existingWinningVector);

            return existingWinningVector;
        }
    }
}
