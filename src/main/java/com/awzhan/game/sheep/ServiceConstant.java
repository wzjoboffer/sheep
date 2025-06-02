package com.awzhan.game.sheep;

import java.util.Random;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ServiceConstant {
    public static final Random RANDOM = new Random();

    public static final String IMAGE_PATH = "image/";
    public static final String PNG_EXT = ".png";
    public static final String GRAY_PNG_EXT = "_gray.png";

    public static final String MUSIC_PATH = "music/";
}
