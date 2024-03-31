package com.example.tiktaktoeio;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class MainController {
    public static final int BOARD_SIZE = 5;
    public GridPane gridPane;
    public Pane strikePan;
    public AnchorPane anchorPane;

    private Game game;
    private CellStriker cellStriker;

    @FXML
    void initialize() {
        this.cellStriker = new CellStriker(strikePan, BOARD_SIZE);
        game = new Game(BOARD_SIZE, this.cellStriker);
        this.gridPane = new GridPaneGenerator(BOARD_SIZE, this).generateGridPane();
        anchorPane.getChildren().addFirst(gridPane);
    }

    @FXML
    void onFieldBtnClick(Button button, int rowIndex, int columnIndex) {
        boolean moved = game.move(rowIndex, columnIndex);
        if (moved) {
            updateGameState();
            button.setText(String.valueOf(game.getCurrentPlayer()));
            game.switchPlayer();
        }
    }

    @FXML
    void onResetBtnClick() {
        clearGameField();
        activateGameField();
        game = new Game(BOARD_SIZE, this.cellStriker);
    }

    private void updateGameState() {
        if (game.isGameOver()) {
            deactivateGameField();
            System.out.println(game.getWinner());
        }
    }

    private void clearGameField() {
        gridPane.getChildren().stream()
                .filter(node -> node instanceof Button)
                .map(node -> (Button) node)
                .forEach(button -> button.setText(""));
        strikePan.getChildren().clear();
    }

    private void deactivateGameField() {
        gridPane.getChildren().stream()
                .filter(node -> node instanceof Button)
                .forEach(node -> node.setDisable(true));
    }

    private void activateGameField() {
        gridPane.getChildren().stream()
                .filter(node -> node instanceof Button)
                .forEach(node -> node.setDisable(false));
    }
}
