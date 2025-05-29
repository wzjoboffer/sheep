package com.awzhan.game.sheep.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {
    private int state;
    private Brand brand;
    private Layer layer;

    public Cell() {
        this.state = 0;
        this.brand = null;
    }

    public Cell(Brand brand) {
        this.state = 1;
        this.brand = brand;
    }
}
