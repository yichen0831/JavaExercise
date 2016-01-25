package practice.matrix;

import java.util.ArrayList;
import java.util.List;

public class SparseMatrix {

    int m;
    int n;
    List<int[]> elements;

    public SparseMatrix() {
        this(1, 1);
    }

    public SparseMatrix(int m, int n) {
        this.m = m < 1 ? 1 : m;
        this.n = n < 1 ? 1 : n;

        elements = new ArrayList<>();
    }

    public void addElement(int[] element) {
        elements.add(element);
    }

    public int[][] getOriginalMatrix() {
        int[][] originalMatrix = new int[n][m];

        elements.stream()
                .filter(e -> e.length == 3)
                .filter(e -> e[0] >= 0 && e[0] < m && e[1] >= 0 && e[1] < n)
                .forEach(e -> originalMatrix[e[1]][e[0]] = e[2]);

        return originalMatrix;
    }

    public static void printArray(int[][] array) {
        for (int[] anArray : array) {
            System.out.print("[");
            for (int i = 0; i < anArray.length; i++) {
                System.out.print(anArray[i] + ((i != anArray.length - 1) ? ", " : ""));
            }
            System.out.println("]");
        }

    }

    public static void main(String[] args) {

        SparseMatrix sparseMatrix = new SparseMatrix(5, 4);
        sparseMatrix.addElement(new int[] {1, 2, 3});
        sparseMatrix.addElement(new int[] {4, 1, 6});
        sparseMatrix.addElement(new int[] {0, 3, 9});

        printArray(sparseMatrix.getOriginalMatrix());
    }
}
