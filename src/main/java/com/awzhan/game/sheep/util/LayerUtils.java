package com.awzhan.game.sheep.util;

import com.awzhan.game.sheep.model.Brand;
import com.awzhan.game.sheep.model.Cell;
import com.awzhan.game.sheep.model.Layer;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LayerUtils {

    public static Layer buildLayer(final int rowNum, final int colNum) {
        final Layer layer = new Layer(rowNum, colNum);
        final Cell[][] matrix = layer.getMatrix();
        final Brand[] brands = BrandUtils.buildBrands(layer.getCapacity(), layer.getBgColor());

        int i = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                final Brand brand = brands[i++];
                matrix[row][col] = new Cell(brand);
            }
        }
        return layer;
    }

    public static void main(String[] args) {
        final Layer layer = buildLayer(3, 5);
        layer.printMatrix();
    }
}
