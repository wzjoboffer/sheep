package com.awzhan.game.sheep.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Layer {
    private Cell[][] matrix;

    public Layer(int x, int y) {
        this.matrix = new Cell[x][y];
    }
}
