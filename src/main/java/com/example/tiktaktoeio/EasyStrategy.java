package com.example.tiktaktoeio;

import java.util.Arrays;
import java.util.List;

public class EasyStrategy extends BaseStrategy{
    public EasyStrategy(Game game) {
        super(game);
    }
    @Override
    public int[] getCoordinate() {
        WinningVector selectedVector = selectVector();
        if (selectedVector != null) {
            List<int[]> coordinates = selectedVector.getVector();
            int index = selectVectorIndex(selectedVector);
            int[] coordinate = coordinates.get(index);

            boolean moveAction = game.move(coordinate[0], coordinate[1]);
            if(moveAction) {
                return coordinate;
            }
            return null;
        }
        else {
            int[] coordinate = findFirstAvailableCell();
            System.out.println(STR."NO MORE VECTORS FOUND: \{Arrays.toString(coordinate)}");
            boolean moveAction = this.game.move(coordinate[0], coordinate[1]);
            if(moveAction) {
                return coordinate;
            }
        }
        return null;
    }

    private WinningVector selectVector() {
        List<WinningVector> vectors = this.findAvailableVectors();
        System.out.println("Potential Winning Vectors:");
        for (WinningVector vector : vectors) {
            System.out.println(vector.toString());
        }

        if (!vectors.isEmpty()) {
            return vectors.getFirst();
        }
        return null;
    }

    private int selectVectorIndex(WinningVector vector) {
        System.out.println(vector);
        WinningVector checkedVector = getWinningVector(vector);

        int index = checkedVector.getUnusedVectorIndex().getFirst();
        System.out.println(index);
        checkedVector.setUsedVectorIndex(index);
        return index;
    }
}
