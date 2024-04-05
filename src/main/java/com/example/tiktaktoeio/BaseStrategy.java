package com.example.tiktaktoeio;

import java.util.ArrayList;
import java.util.List;

abstract public class BaseStrategy {
    private final Game game;

    private final List<WinningVector> winningVectorPool;

    public BaseStrategy(Game game) {
        this.game = game;
        this.winningVectorPool = new ArrayList<>();
    }

    abstract WinningVector selectVector();
    abstract public int selectVectorIndex(WinningVector vector);

    public List<WinningVector> findAvailableVectors() {
        char opponentPlayer = this.game.getOpponentPlayer();
        char[][] gameField = this.game.getBoard();

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

    public int[] findFirstAvailableCell() {
        char[][] board = game.getBoard();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Winner.EMPTY_CELL) {

                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

    public void setWinningVector(WinningVector winningVector) {
        this.winningVectorPool.add(winningVector);
    }
    public WinningVector getWinningVector(WinningVector existingWinningVector) {
        if (existingWinningVector != null && winningVectorPool.contains(existingWinningVector)) {
            return winningVectorPool.get(winningVectorPool.indexOf(existingWinningVector));
        } else {
            this.setWinningVector(existingWinningVector);
            return existingWinningVector;
        }
    }
}
