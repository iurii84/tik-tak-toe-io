package com.example.tiktaktoeio;

import java.util.List;

public class EasyStrategy extends BaseStrategy{
    public EasyStrategy(Game game) {
        super(game);
    }

    @Override
    public WinningVector selectVector() {
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

    public int selectVectorIndex(WinningVector vector) {
        // TODO
        System.out.println(vector);
        WinningVector checkedVector = getWinningVector(vector);

        int index = checkedVector.getUnusedVectorIndex().getFirst();
        System.out.println(index);
        checkedVector.setUsedVectorIndex(index);
        return index;
    }
}
