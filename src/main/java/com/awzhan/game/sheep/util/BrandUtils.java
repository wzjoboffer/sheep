package com.awzhan.game.sheep.util;

import static com.awzhan.game.sheep.ServiceConstant.GRAY_PNG_EXT;
import static com.awzhan.game.sheep.ServiceConstant.PNG_EXT;
import static com.awzhan.game.sheep.ServiceConstant.RANDOM;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.awzhan.game.sheep.model.Brand;
import com.awzhan.game.sheep.model.Cell;
import com.awzhan.game.sheep.model.Layer;

import org.apache.commons.lang3.StringUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BrandUtils {
    private static final String[] brandNames = {
            "apple", "barbeque", "broccoli", "cabbage", "carrots",
            "cherries", "crab", "eggplant", "flamingo", "gardening",
            "grape", "home", "jellyfish", "juice", "livestock",
            "mouse", "potato", "turtle", "watering", "watermelon"
    };

    public static String getRandomBrandName() {
        int idx = RANDOM.nextInt(brandNames.length);
        return brandNames[idx];
    }

    public static Brand[] buildBrands(final int capacity, final Color bgColor) {
        final Brand[] brands = new Brand[capacity];

        int i = 0;
        while (i < brands.length) {
            final String brandName = getRandomBrandName();
            brands[i++] = new Brand(brandName);
            brands[i++] = new Brand(brandName);
            brands[i++] = new Brand(brandName);
        }
        for (Brand brand : brands) {
            brand.setBgColor(bgColor);
        }
        return shuffle(brands);
    }

    public static Brand[] shuffle(final Brand[] brands) {
        for (int i = brands.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);

            Brand temp = brands[i];
            brands[i] = brands[j];
            brands[j] = temp;
        }
        return brands;
    }

    public static boolean refresh(final Brand brand, final Layer layer) {
        if (layer == null) {
            return false;
        }

        final Cell[][] matrix = layer.getMatrix();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].getState() == 0) {
                    continue;
                }

                final Brand other = matrix[row][col].getBrand();
                if (brand.getBounds().intersects(other.getBounds())) {
                    return true;
                }
            }
        }

        return refresh(brand, layer.getParent());
    }

    public static void printAllBrandNames(final String dir) {
        try (Stream<Path> pathStream = Files.walk(Paths.get(dir), 1)) {
            final String[] allBrandNames = pathStream.filter(Files::isRegularFile)
                    .filter(path -> StringUtils.endsWith(path.toString(), PNG_EXT))
                    .filter(path -> !StringUtils.endsWith(path.toString(), GRAY_PNG_EXT))
                    .map(path -> {
                        String[] parts = StringUtils.split(path.toString(), "/");
                        return StringUtils.remove(parts[parts.length - 1], PNG_EXT);
                    })
                    .sorted()
                    .toArray(String[]::new);

            System.out.println("There are " + allBrandNames.length + " brands.");
            for (String brandName : allBrandNames) {
                System.out.print("\"" + brandName + "\", ");
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        printAllBrandNames("/Volumes/Unix/awzhan/game/sheep/src/main/resources/images/");
    }
}
