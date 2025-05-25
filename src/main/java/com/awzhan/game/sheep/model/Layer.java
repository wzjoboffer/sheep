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
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.capacity = rowNum * colNum;
        this.size = 0;
        this.matrix = new Cell[rowNum][colNum];
    }
}
