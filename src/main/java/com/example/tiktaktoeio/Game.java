package com.example.tiktaktoeio;

public class Game {
    private final char[][] board;
    private boolean gameOver;
    private char currentPlayer;
    private final int boardSize;
    private int[][] winningCells;
    private final CellStriker cellStriker;
    private final ComputerPlayer computerPlayer;
    private final BaseStrategy strategy;

    public Game(int boardSize, CellStriker cellStriker) {
        this.boardSize = boardSize;
        this.cellStriker = cellStriker;
        this.board = new char[boardSize][boardSize];
        this.gameOver = false;
        this.currentPlayer = 'X';
        this.strategy = new EasyStrategy(this);
        this.computerPlayer = new ComputerPlayer(this, this.strategy);
    }

    public boolean move(int row, int col) {
        if (!isValidMove(row, col) || gameOver) {
            return false;
        }

        board[row][col] = currentPlayer;
        if (checkWinner()) {
            gameOver = true;
            winningCells = calculateWinningCells();
            this.cellStriker.strike(this.winningCells);
        } else if (isBoardFull()) {
            gameOver = true;
        }

        return true;
    }

    public int[] computerMove() {
        return computerPlayer.move();
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < boardSize && col >= 0 && col < boardSize && board[row][col] == Winner.EMPTY_CELL;
    }

    private boolean checkWinner() {
        Winner winner = new Winner(board);

        return winner.haveWinner();
    }

    private int[][] calculateWinningCells() {
        Winner winner = new Winner(board);

        return winner.getWinningCells();
    }

    private boolean isBoardFull() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == Winner.EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public char[][] getBoard() {
        return board;
    }

    public int[][] getWinningCells() {
        return winningCells;
    }

    public char getWinner() {
        if (gameOver && winningCells != null) {
            return board[winningCells[0][0]][winningCells[0][1]];
        }

        return Winner.EMPTY_CELL; // Return null character if there's no winner
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
    public char getOpponentPlayer() {
        return currentPlayer == 'X' ? 'O' : 'X';
    }
}
