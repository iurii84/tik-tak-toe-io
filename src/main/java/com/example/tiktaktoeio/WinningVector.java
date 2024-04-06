    package com.example.tiktaktoeio;

    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;
    import java.util.Objects;

    public class WinningVector {
        private final List<int[]> vector;
        private final List<Integer> usedVectorIndices;

        public WinningVector() {
            this.vector = new ArrayList<>();
            this.usedVectorIndices = new ArrayList<>();
        }

        public void setUsedVectorIndex(int usedVectorIndex) {
            usedVectorIndices.add(usedVectorIndex);
        }

        public List<Integer> getUnusedVectorIndex() {
            List<Integer> freeIndices = new ArrayList<>();

            for (int i = 0; i < vector.size(); i++) {
                if (!usedVectorIndices.contains(i)) {
                    freeIndices.add(i);
                }
            }
            System.out.println(STR."FREE INDICES\{freeIndices}");
            System.out.println(STR."USED INDICES\{usedVectorIndices}");
            return freeIndices;

        }

        public List<int[]> getVector() {
            return vector;
        }

        public void setCoordinate(int[] coordinate) {
            vector.add(coordinate);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WinningVector that = (WinningVector) o;

            return areVectorsEqual(this.vector, that.vector);
        }

        private boolean areVectorsEqual(List<int[]> vector1, List<int[]> vector2) {
            if (vector1.size() != vector2.size()) {
                return false;
            }
            for (int i = 0; i < vector1.size(); i++) {
                if (!Arrays.equals(vector1.get(i), vector2.get(i))) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            return Objects.hash(vector);
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("Vector: ");
            for (int[] v : vector) {
                stringBuilder.append(STR."(\{v[0]}, \{v[1]}) ");
            }
            return stringBuilder.toString();
        }
    }
