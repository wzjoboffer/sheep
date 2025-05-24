package com.awzhan.game.sheep.util;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

@UtilityClass
public class ImageUtils {

    public static void convertToGray(@NonNull final String srcImagePath) {
        try (final FileInputStream fis = new FileInputStream(srcImagePath)) {
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

            final String dstImagePath = getDstImagePath(srcImagePath);
            ImageIO.write(dstImage, "png", new File(dstImagePath));
            System.out.println("New image generated at: " + dstImagePath);
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

    private static String getDstImagePath(final String srcImagePath) {
        int idx = srcImagePath.lastIndexOf(".");
        return srcImagePath.substring(0, idx) + "_gray" + srcImagePath.substring(idx);
    }

    public static void main(String[] args) {
        convertToGray("/Volumes/Unix/awzhan/game/sheep/src/main/resources/images/wei.png");
    }
}
