package com.example.tiktaktoeio;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {
    public TextField namePlayerOneField;
    public TextField namePlayerTwoField;

    public Label playerOneLabel;
    public Label playerTwoLabel;
    public Pane playerTwoPane;
    public ChoiceBox<String> fieldSizeChoiceBox;

    private String selectedPlayer = "player";
    private String playerOneName;
    private String playerTwoName;
    public Pane menuPane;
    @FXML
    private ChoiceBox<String> playWithChoiceBox;
    private int fieldSizeSelected;
    private mainControllerInterface mainController;

    public void initialize() {
        initializeChoiceBoxPlayWith();
        initializeNameFields();
        initializeChoiceBoxFieldSize();
    }

    public void setMainController(mainControllerInterface mainController) {
        this.mainController = mainController;
    }

    public String getSelectedPlayer() {
        return selectedPlayer;
    }

    public int getFieldSize() {
        return this.fieldSizeSelected;
    }

    private void initializeChoiceBoxPlayWith() {
        playWithChoiceBox.getItems().addAll("player", "computer");
        playWithChoiceBox.setValue("player");

        initPlayerLabels();

        playWithChoiceBox.setOnAction((_) -> {
            onChoiceBoxPlayWithSelected();
        });
    }

    private void initializeChoiceBoxFieldSize() {
        fieldSizeChoiceBox.getItems().addAll("3 X 3", "4 X 4", "5 X 5");
        fieldSizeChoiceBox.setValue("3 X 3");

        fieldSizeChoiceBox.setOnAction((_) -> {
            onChoiceBoxFieldSizeSelected();
        });
    }

    private void onChoiceBoxFieldSizeSelected() {
        fieldSizeSelected = fieldSizeChoiceBox.getSelectionModel().getSelectedIndex() + 3;
        if (mainController != null) {
            mainController.setBoardSize(fieldSizeSelected);
            mainController.resetGridPane();
        }
    }

    private void onChoiceBoxPlayWithSelected() {
        selectedPlayer = playWithChoiceBox.getSelectionModel().getSelectedItem();
        initPlayerLabels();

        playerTwoPane.setVisible(!selectedPlayer.equals("computer"));

        namePlayerOneField.setDisable(false);
        namePlayerTwoField.setDisable(false);

        System.out.println(STR."Selection made: \{selectedPlayer}");
    }

    private void initPlayerLabels() {
        if (selectedPlayer.equals("computer")) {
            playerOneLabel.setText("Enter your name");
        }
        else {
            playerOneLabel.setText("Player 1 name");
            playerTwoLabel.setText("Player 2 name");
        }
    }

    private void initializeNameFields() {
        namePlayerOneField.setOnAction((_) -> {
            onInputPlayerOneField();
        });

        namePlayerTwoField.setOnAction((_) -> {
            onInputPlayerTwoField();
        });

    }

    private void onInputPlayerOneField() {
        playerOneName = namePlayerOneField.getText();
        namePlayerOneField.setDisable(true);

        System.out.println(STR."Player 1: \{playerOneName}");
    }

    private void onInputPlayerTwoField() {
        playerTwoName = namePlayerTwoField.getText();
        namePlayerTwoField.setDisable(true);

        System.out.println(STR."Player 2: \{playerOneName}");
    }

    public void deactivateChoiceBoxFieldSize() {
        System.out.println("deactivate");
        fieldSizeChoiceBox.setDisable(true);
    }

    public void activateChoiceBoxFieldSize() {
        System.out.println("activate");
        fieldSizeChoiceBox.setDisable(false);
    }
}
