package com.awzhan.game.sheep.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.awzhan.game.sheep.model.Brand;
import com.awzhan.game.sheep.model.Cell;
import com.awzhan.game.sheep.model.GameMap;
import com.awzhan.game.sheep.model.Layer;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GameMapUtils {

    public static GameMap buildGameMap(final int level) {
        List<Layer> layers = new ArrayList<>();
        int i = 0;
        while (i < level) {
            final Layer layer = LayerUtils.buildLayer(6, 6);
            if (i > 0) {
                layer.setParent(layers.get(i-1));
            }
            layers.add(layer);
            i++;
        }
        layers = Collections.unmodifiableList(layers);

        final GameMap gameMap = new GameMap(layers.size(), layers);
        return gameMap;
    }

    public static void refreshAll(final GameMap gameMap) {
        System.out.println("refreshAll() is called");
        final List<Layer> layers = gameMap.getLayers();
        for (int i = 1; i < layers.size(); i++) {
            final Layer layer = layers.get(i);
            final Cell[][] matrix = layer.getMatrix();
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[row].length; col++) {
                    final Cell cell = matrix[row][col];
                    if (cell.getState() == 0) {
                        continue;
                    }
                    final Brand brand = cell.getBrand();
                    boolean res = BrandUtils.refresh(brand, layer.getParent());
                    brand.setGray(res);
                }
            }
        }
    }
}
