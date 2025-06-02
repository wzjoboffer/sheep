package com.awzhan.game.sheep.util;

import static com.awzhan.game.sheep.ServiceConstant.MUSIC_PATH;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MusicUtil {

    public static void play(final String filename) {
        final URL fileUrl = MusicUtil.class.getClassLoader().getResource(MUSIC_PATH + filename);
        try (BufferedInputStream bis = new BufferedInputStream(fileUrl.openStream())) {
            Player player = new Player(bis);
            player.play();
        } catch (IOException | JavaLayerException e) {
            e.printStackTrace();
        }
    }
}
