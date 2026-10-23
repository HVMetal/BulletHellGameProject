package org.bullethell.game.audio.management;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import org.bullethell.game.utils.PropertyProvider;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class SoundCache {
    private final static Map<String, Sound> cache = new HashMap<>();

    private SoundCache() {}

    public static void load(String key, String sfxPath) {
        cache.put(key, TinySound.loadSound(new File(
                PropertyProvider.getProperty("AUDIO_FOLDER_PATH") + sfxPath)
        ));
    }

    public static void play(String key) {
        final Sound sfx = cache.get(key);
        if(sfx != null) {
            sfx.stop();
            sfx.play();
        }
    }
}
