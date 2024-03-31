package com.example.tiktaktoeio;

public class Winner {
    public static final char EMPTY_CELL = '\u0000';
    private final char winningSymbol;
    private int[][] winningCells;
    private final char[][] board;
    private final int boardSize;

    public Winner(char[][] board) {
        this.board = board;
        this.boardSize = board.length; // Assuming the board is square
        this.winningSymbol = findWinningSymbol();
    }

    private char findWinningSymbol() {
        char firstCell;
        // Check rows
        for (int row = 0; row < boardSize; row++) {
            firstCell = board[row][0];
            if (firstCell != EMPTY_CELL && isWinningLine(row, 0, 0, 1))
                return firstCell;
        }

        // Check columns
        for (int col = 0; col < boardSize; col++) {
            firstCell = board[0][col];
            if (firstCell != EMPTY_CELL && isWinningLine(0, col, 1, 0))
                return firstCell;
        }

        // Check diagonal
        firstCell = board[0][0];
        if (firstCell != EMPTY_CELL && isWinningLine(0, 0, 1, 1))
            return firstCell;

        // Check anti-diagonal
        firstCell = board[0][boardSize - 1];
        if (firstCell != EMPTY_CELL && isWinningLine(0, boardSize - 1, 1, -1))
            return firstCell;

        winningCells = new int[0][0]; // No winning cells found
        return EMPTY_CELL;
    }

    private boolean isWinningLine(int startX, int startY, int deltaX, int deltaY) {
        char firstCell = board[startX][startY];
        this.winningCells = new int[boardSize][2]; // [number of cells][x, y coordinates]

        for (int i = 0; i < boardSize; i++) {
            int x = startX + i * deltaX;
            int y = startY + i * deltaY;

            if (x < 0 || x >= boardSize || y < 0 || y >= boardSize || board[x][y] != firstCell) {
                return false;
            }

            winningCells[i][0] = x;
            winningCells[i][1] = y;
        }
        return true;
    }


    public char getWinningSymbol() {
        return winningSymbol;
    }

    public int[][] getWinningCells() {
        return winningCells;
    }

    public boolean haveWinner() {
        return getWinningSymbol() != EMPTY_CELL;
    }
}
