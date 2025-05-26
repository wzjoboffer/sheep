package com.awzhan.game.sheep.util;

import static com.awzhan.game.sheep.ServiceConstant.GRAY_PNG_EXT;
import static com.awzhan.game.sheep.ServiceConstant.PNG_EXT;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

import com.awzhan.game.sheep.model.Brand;

import org.apache.commons.lang3.StringUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BrandUtils {
    private static final Random random = new Random();

    private static final String[] brandNames = {
        "apple", "broccoli", "cabbage", "carrots", "cherries",
        "crab", "dynamodb", "ec2", "eggplant", "flamingo",
        "grape", "iam", "jellyfish", "juice", "lambda",
        "livestock", "mouse", "potato", "sqs", "turtle",
        "watermelon"
    };

    public static String getRandomBrandName() {
        int idx = random.nextInt(brandNames.length);
        return brandNames[idx];
    }

    public static Brand[] buildBrands(final int capacity) {
        final Brand[] brands = new Brand[capacity];

        int i = 0;
        while (i < brands.length) {
            final String brandName = getRandomBrandName();
            brands[i++] = new Brand(brandName);
            brands[i++] = new Brand(brandName);
            brands[i++] = new Brand(brandName);
        }
        return shuffle(brands);
    }

    public static Brand[] shuffle(final Brand[] brands) {
        for (int i = brands.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            Brand temp = brands[i];
            brands[i] = brands[j];
            brands[j] = temp;
        }
        return brands;
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
