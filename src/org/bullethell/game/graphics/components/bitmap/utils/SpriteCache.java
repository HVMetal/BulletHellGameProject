package org.bullethell.game.graphics.components.bitmap.utils;

import org.bullethell.game.graphics.components.bitmap.Sprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SpriteCache {
    private final static Map<String, Sprite> cache = new HashMap<>();

    public static void addSprite(String key, String filename) {
        final BufferedImage buffImg = ImageUtils.loadImage(filename);
        final Sprite sprite = new Sprite(key, buffImg);

        cache.put(key, sprite);
    }

    public static void addSpriteSequence(String key, String filename, int animationFrames) {
        final List<BufferedImage> buffImgList = ImageUtils.loadAnimation(filename, animationFrames);
        final int listSize = buffImgList.size();

        for(int i = 0; i < listSize; i++) {
            final String newKey = key + i;
            final BufferedImage buffImg = buffImgList.get(i);
            final Sprite sprite = new Sprite(newKey, buffImg);

            cache.put(newKey, sprite);
        }
    }

    public static Sprite getSprite(String key) {
        return cache.get(key);
    }

    public static List<Sprite> getSpriteSequence(String key) {
        final List<Sprite> spriteSequence = new ArrayList<>();
        int i = 0;

        Sprite sprite = getSprite(key + (i++));
        while(sprite != null) {
            spriteSequence.add(sprite);
            sprite = getSprite(key + (i++));
        }

        return spriteSequence;
    }
}
