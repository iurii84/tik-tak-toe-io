package com.example.tiktaktoeio;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class MenuController {
    public TextField namePlayerOneField;
    public TextField namePlayerTwoField;

    public Label playerOneLabel;
    public Label playerTwoLabel;
    public Pane playerTwoPane;

    private String selectedPlayer = "player";
    private String playerOneName;
    private String playerTwoName;
    public Pane menuPane;
    @FXML
    private ChoiceBox<String> playWithChoiceBox;

    public String getSelectedPlayer() {
        return selectedPlayer;
    }

    public void initialize() {
        initializeChoiceBoxPlayWith();
        initializeNameFields();


    }

    private void initializeChoiceBoxPlayWith() {
        playWithChoiceBox.getItems().addAll("player", "computer");
        playWithChoiceBox.setValue("player");

        initPlayerLabels();

        playWithChoiceBox.setOnAction((event) -> {
            onChoiceBoxPlayWithSelected();
        });
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
        namePlayerOneField.setOnAction((event) -> {
            onInputPlayerOneField();
        });

        namePlayerTwoField.setOnAction((event) -> {
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
}
