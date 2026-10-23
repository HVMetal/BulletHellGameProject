package org.bullethell.game.utils;

import java.awt.*;

public final class TextUtils {
    public static Font getIdealSizedFont(Font originalFont, String text, int containerWidth) {
        return getIdealSizedFont(originalFont, text, containerWidth, 1);
    }

    public static Font getIdealSizedFont(Font originalFont, String text, int containerWidth, float finesse) {
        final float idealFontSize = getIdealFontSize(originalFont, text, containerWidth, finesse);
        return originalFont.deriveFont(idealFontSize);
    }

    public static int getStringWidth(Font font, String text) {
        final Canvas canvas = new Canvas();
        final FontMetrics metrics = canvas.getFontMetrics(font);

        return metrics.stringWidth(text);
    }

    private static float getIdealFontSize(Font originalFont, String text, int containerWidth, float finesse) {
        final Canvas canvas = new Canvas();
        FontMetrics metrics = canvas.getFontMetrics(originalFont);

        Font smallerFont = originalFont.deriveFont(originalFont.getStyle(), originalFont.getSize());
        float newFontSize = originalFont.getSize();

        while (metrics.stringWidth(text) > containerWidth) {
            newFontSize -= finesse;
            smallerFont = smallerFont.deriveFont(newFontSize);
            metrics = canvas.getFontMetrics(smallerFont);
        }

        return newFontSize;
    }
}
