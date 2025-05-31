package com.awzhan.game.sheep.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class GameMap {

    private final int level;

    private final List<Layer> layers;

    private final EliminateBox eliminateBox;

    public GameMap(int level) {
        this.level = level;
        this.layers = new ArrayList<>(level);
        this.eliminateBox = new EliminateBox();
    }

    public GameMap(int level, List<Layer> layers, EliminateBox eliminateBox) {
        if (level != layers.size()) {
            throw new IllegalArgumentException("level is not equal to the size of layers");
        }

        this.level = level;
        this.layers = layers;
        if (layers != null) {
            for (Layer layer : layers) {
                layer.setGameMap(this);
            }
        }
        this.eliminateBox = eliminateBox;
    }
}
