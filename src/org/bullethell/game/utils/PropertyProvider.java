package org.bullethell.game.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyProvider {
    private final static Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = PropertyProvider.class.getResourceAsStream("/application.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
