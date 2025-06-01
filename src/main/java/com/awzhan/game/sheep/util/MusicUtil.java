package com.awzhan.game.sheep.util;

import static com.awzhan.game.sheep.ServiceConstant.IMAGE_PATH;
import static com.awzhan.game.sheep.ServiceConstant.PNG_EXT;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import com.awzhan.game.sheep.model.Brand;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MusicUtil {

    public static void play(final String filename) {
        final String filePath = Brand.class.getClassLoader().getResource(filename).toString();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
            Player player = new Player(bis);
            player.play();
        } catch (IOException | JavaLayerException e) {
            e.printStackTrace();
        }
    }
}
