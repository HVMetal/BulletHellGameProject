package org.bullethell.game.audio.management;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;
import org.bullethell.game.utils.PropertyProvider;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class MusicPlayer {
    private final static Map<String, Music> cache = new HashMap<>();
    private static String currentMusic;

    private MusicPlayer() {}

    public static void load(String key, String sfxPath) {
        cache.put(key, TinySound.loadMusic(new File(
                PropertyProvider.getProperty("AUDIO_FOLDER_PATH") + sfxPath
        ), true));
    }

    public static void play(String key) {
        play(key, true);
    }

    public static void play(String key, boolean loop) {
        if(currentMusic != null) {
            cache.get(currentMusic).stop();
        }

        currentMusic = key;
        cache.get(key).play(loop);
    }

    public static void stop() {
        cache.forEach((_, music) -> music.stop());
    }

    public static void rewind() {
        cache.forEach((_, music) -> music.rewind());
    }

    public static void pause() {
        if(currentMusic == null) {
            return;
        }
        cache.get(currentMusic).pause();
    }

    public static void unpause() {
        if(currentMusic == null) {
            return;
        }
        cache.get(currentMusic).resume();
    }

    public static void setVolume(double volume) {
        cache.forEach((_, music) -> music.setVolume(volume));
    }

    public static double getVolume() {
        if(currentMusic == null) {
            return 0;
        }
        return cache.get(currentMusic).getVolume();
    }
}
