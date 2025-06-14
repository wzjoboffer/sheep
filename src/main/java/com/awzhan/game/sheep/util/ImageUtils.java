package com.awzhan.game.sheep.util;

import static com.awzhan.game.sheep.ServiceConstant.GRAY_PNG_EXT;
import static com.awzhan.game.sheep.ServiceConstant.PNG_EXT;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ImageUtils {

    public static void convertToGray(@NonNull final String srcImageFilename) {
        try (final FileInputStream fis = new FileInputStream(srcImageFilename)) {
            final BufferedImage srcImage = ImageIO.read(fis);
            int width = srcImage.getWidth();
            int height = srcImage.getHeight();

            final BufferedImage dstImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    int RGB = srcImage.getRGB(w, h);
                    int R = (RGB & 0xff0000) >> 16;
                    int G = (RGB & 0x00ff00) >> 8;
                    int B = RGB & 0x0000ff;

                    int gray = (int) (R * 0.299 + G * 0.587 + B * 0.114);
                    int newRGB = colorToRGB(gray, gray, gray);
                    dstImage.setRGB(w, h, newRGB);
                }
            }

            final String dstImageFilename = getGrayImageFilename(srcImageFilename);
            ImageIO.write(dstImage, "png", new File(dstImageFilename));
            System.out.println("New image generated at: " + dstImageFilename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int colorToRGB(int r, int g, int b) {
        int res = 0;
        res <<= 8;
        res += r;
        res <<= 8;
        res += g;
        res <<= 8;
        res += b;
        return res;
    }

    private static String getGrayImageFilename(final String srcImagePath) {
        return StringUtils.replace(srcImagePath, PNG_EXT, GRAY_PNG_EXT);
    }

    public static void convertImages(final String dir) {
        if (!Files.isDirectory(Paths.get(dir))) {
            throw new IllegalArgumentException(dir + " is not a directory.");
        }

        try (Stream<Path> pathStream = Files.walk(Paths.get(dir), 1)) {
            pathStream.filter(Files::isRegularFile)
                    .filter(path -> StringUtils.endsWith(path.toString(), PNG_EXT))
                    .filter(path -> !StringUtils.endsWith(path.toString(), GRAY_PNG_EXT))
                    .forEach(path -> {
                        String grayImageFilename = getGrayImageFilename(path.toString());
                        if (Files.exists(Paths.get(grayImageFilename))) {
                            System.out.println("Skip " + path + ", because gray image already exists.");
                            return;
                        }
                        convertToGray(path.toString());
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final String dir = "/Volumes/Unix/awzhan/game/sheep/src/main/resources/images/";
        convertImages(dir);
    }
}
