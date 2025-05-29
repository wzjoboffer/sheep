package com.awzhan.game.sheep.model;

import static com.awzhan.game.sheep.ServiceConstant.RANDOM;

import java.awt.Color;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Layer {
    private static int[] OFFSET = {25, 50, 75, 100, 125, 150};
    private static Color[] BG_COLOR = {
            Color.blue, Color.cyan, Color.gray, Color.green, Color.magenta, Color.orange, Color.pink,  Color.yellow
    };

    private final int rowNum;
    private final int colNum;

    private final int capacity;
    private int size;

    private final Cell[][] matrix;

    @Setter
    private int offsetX;
    @Setter
    private int offsetY;
    @Setter
    private Color bgColor;
    @Setter
    private Layer parent;
    @Setter
    private GameMap gameMap;

    public Layer(int rowNum, int colNum) {
        this.capacity = rowNum * colNum;
        if (this.capacity % 3 != 0) {
            throw new IllegalArgumentException("Capacity is not multiplier of 3: " + this.capacity);
        }

        this.rowNum = rowNum;
        this.colNum = colNum;
        this.size = 0;
        this.matrix = new Cell[rowNum][colNum];

        this.offsetX = OFFSET[RANDOM.nextInt(OFFSET.length)];
        this.offsetY = OFFSET[RANDOM.nextInt(OFFSET.length)];
        this.bgColor = BG_COLOR[RANDOM.nextInt(BG_COLOR.length)];
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
