package com.awzhan.game.sheep.model;

import lombok.Getter;

@Getter
public class Layer {
    private final int rowNum;
    private final int colNum;
    private final int capacity;
    private int size;
    private final Cell[][] matrix;

    public Layer(int rowNum, int colNum) {
        this.capacity = rowNum * colNum;
        if (this.capacity % 3 != 0) {
            throw new IllegalArgumentException("Capacity is not multiplier of 3: " + this.capacity);
        }

        this.rowNum = rowNum;
        this.colNum = colNum;
        this.size = 0;
        this.matrix = new Cell[rowNum][colNum];
    }

    public void printMatrix() {
        for (int row = 0; row < matrix.length; row++) {
            System.out.print("| ");
            for (int col = 0; col < matrix[row].length; col++) {
                final Brand brand = matrix[row][col].getBrand();
                System.out.print(brand + " | ");
            }
            System.out.println();
        }
    }
}
