package com.example.tiktaktoeio;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class MainController implements mainControllerInterface{
    private int boardSize = 3;
    public GridPane gridPane;
    public Pane strikePan;
    public AnchorPane anchorPane;
    public Button startBtn;
    public VBox menuVbox;

    private Game game;
    private CellStriker cellStriker;
    private MenuController menuController;

    @FXML
    void initialize() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        try {
            menuVbox.getChildren().add(loader.load());
            menuController = loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        menuController = loader.getController();

        // instantiate menu controller with an instance of main controller
        menuController.setMainController(this);
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
        game = new Game(boardSize, this.cellStriker);
        activateStartBtn();
        menuController.activateChoiceBoxFieldSize();
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

    @Override
    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public void onStartBtnClick(ActionEvent actionEvent) {
        resetGridPane();
        this.cellStriker = new CellStriker(strikePan, boardSize);
        game = new Game(boardSize, this.cellStriker);
        this.gridPane = new GridPaneGenerator(boardSize, this).generateGridPane();
        anchorPane.getChildren().addFirst(gridPane);
        deactivateStartBtn();
        menuController.deactivateChoiceBoxFieldSize();
    }

    public void resetGridPane() {
        if (gridPane != null) {
            anchorPane.getChildren().remove(gridPane);
            clearGameField();
        }
    }
    private void deactivateStartBtn() {
        startBtn.setDisable(true);
    }

    private void activateStartBtn() {
        startBtn.setDisable(false);
    }
}
