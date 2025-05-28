package com.awzhan.game.sheep.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}
