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
        for (int i = 0; i < level; i++) {
            final Layer layer = LayerUtils.buildLayer(3, 3);
            layers.add(layer);
        }
        layers = Collections.unmodifiableList(layers);

        final GameMap gameMap = new GameMap(layers.size(), layers);
        return gameMap;
    }
}
