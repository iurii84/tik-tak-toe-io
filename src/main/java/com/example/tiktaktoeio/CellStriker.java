package com.example.tiktaktoeio;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class CellStriker {
    private final Pane strikePan;
    private final int boardSize;

    public CellStriker(Pane strikePan, int boardSize) {
        this.strikePan = strikePan;

        this.boardSize = boardSize;
    }

    public void strike(int[][] coordinates) {
        this.isCorrectCoordinates(coordinates);

        double[] startPoint = getCellCenterCoordinates(coordinates[0]);
        double[] endPoint = getCellCenterCoordinates(coordinates[boardSize - 1]);

        Line line = new Line(startPoint[0], startPoint[1], endPoint[0], endPoint[1]);
        line.setStroke(javafx.scene.paint.Color.RED);
        line.setStrokeWidth(10);

        strikePan.getChildren().add(line);
    }

    private double[] getCellCenterCoordinates(int[] coordinates) {
        Bounds bounds = strikePan.getBoundsInLocal();

        double minX = bounds.getMinX();
        double minY = bounds.getMinY();
        double width = strikePan.getWidth() / boardSize;
        double height = strikePan.getHeight() / boardSize;
        double centerX = minX + (coordinates[1] + 0.5) * width;
        double centerY = minY + (coordinates[0] + 0.5) * height;

        return new double[]{centerX, centerY};
    }

    private void isCorrectCoordinates(int[][] coordinates) {
        if (coordinates.length != boardSize) {
            throw new IllegalArgumentException(STR."Invalid number of coordinate pairs. Expected \{boardSize} pairs.");
        }

        for (int[] coordinate : coordinates) {
            if (coordinate.length != 2) {
                throw new IllegalArgumentException("Invalid coordinate pair length. Each pair should contain 2 elements.");
            }
        }
    }

}
