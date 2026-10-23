package org.bullethell.game.utils;

public final class KeyGenerator {
    private static int NEXT_VALUE = -1;

    public static int next() {
        if(NEXT_VALUE == Integer.MAX_VALUE) {
            NEXT_VALUE = 0;
        }
        else {
            NEXT_VALUE++;
        }

        return NEXT_VALUE;
    }
}
