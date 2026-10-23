package org.bullethell.game.utils;

public final class MathUtils {
    /**
     * Calculates the Euclidean distance between two points in a 2D space.
     * The distance is determined using the formula:
     * sqrt((x2 - x1)^2 + (y2 - y1)^2).
     *
     * @param a the first point, represented as a {@link Coordinates} object.
     * @param b the second point, represented as a {@link Coordinates} object.
     * @return the Euclidean distance between points a and b as a float.
     */
    public static float euclidDist(Coordinates a, Coordinates b) {
        return (float) Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    /**
     * Calculates the angle (in radians) between the vector connecting point a to b
     * and the positive x-axis, ensuring the angle is in the range [0, 2π).
     * This is done using the atan2 function and adjusts for cases where the
     * x-difference is negative.
     *
     * @param a the starting point, represented as a {@link Coordinates} object.
     * @param b the target point, represented as a {@link Coordinates} object.
     * @return the angle between the two points in radians, as a float.
     */
    public static float atan2(Coordinates a, Coordinates b) {
        final float deltaX = b.getX() - a.getX();
        final float deltaY = b.getY() - a.getY();

        return MathUtils.atan2(deltaY, deltaX);
    }

    public static float atan2(float y, float x) {
        float atanVal = (float) Math.atan2(y, x);

        if (x < 0) {
            atanVal += (float) (2 * Math.PI);
        }

        return atanVal;
    }

    /**
     * Calculates the number of digits of an integer.
     *
     * @param number an integer.
     * @return the number of digits of the integer.
     */
    public static int nrOfDigits(int number) {
        if(number == 0) {
            return 1;
        } else {
            return (int) Math.floor(Math.log10(Math.abs(number)) + 1);
        }
    }
}
