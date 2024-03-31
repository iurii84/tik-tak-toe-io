package com.example.tiktaktoeio;

import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class GridPaneGenerator {
    private final int boardSize;
    private final int elementSide;

    private final int fontSize;
    public static final int SIDE_DIMENSION = 400;
    private final MainController mainController;
    public GridPaneGenerator(int boardSize, MainController mainController) {
        this.boardSize = boardSize;
        this.mainController = mainController;
        this.elementSide = SIDE_DIMENSION / boardSize;
        this.fontSize = Math.min(61, elementSide / 3);
    }

    public GridPane generateGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setMaxSize(SIDE_DIMENSION, SIDE_DIMENSION);
        gridPane.setPrefSize(SIDE_DIMENSION, SIDE_DIMENSION);
        gridPane.setLayoutX(200);

        // Set column constraints
        for (int i = 0; i < this.boardSize; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPrefWidth(this.elementSide);
            column.setHgrow(Priority.SOMETIMES);

            gridPane.getColumnConstraints().add(column);

            RowConstraints row = new RowConstraints();
            row.setPrefHeight(this.elementSide);
            row.setVgrow(Priority.SOMETIMES);

            gridPane.getRowConstraints().add(row);
        }

        // Create buttons and add them to the gridPane
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                Button button = new Button();
                button.setPrefSize(this.elementSide, this.elementSide);
                button.setMaxSize(this.elementSide, this.elementSide);

                button.setFont(new Font("Comic Sans MS", this.fontSize));
                int X = i;
                int Y = j;
                button.setOnAction(_ -> mainController.onFieldBtnClick(button, X, Y));
                gridPane.add(button, j, i);
            }
        }

        return gridPane;
    }
}
