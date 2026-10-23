package org.bullethell.game.graphics.components.shapes.composite;

import org.bullethell.game.graphics.components.shapes.Shape;
import org.bullethell.game.graphics.components.shapes.simple.ShapeCircle;
import org.bullethell.game.graphics.components.shapes.simple.ShapeLine;
import org.bullethell.game.utils.Coordinates;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomCompositeStar extends CompositeShape {
    private final Coordinates centerCoordinates;
    private final float radius, radiusNoise, velocity;
    private final int nrOfLines;
    private final List<Coordinates> lineEndpoints;

    private float angle;

    public CustomCompositeStar(float radius, float radiusNoise, float velocity, float initAngle, int nrOfLines, Color color) {
        this(new Coordinates(), radius, radiusNoise, velocity, initAngle, nrOfLines, color);
    }

    public CustomCompositeStar(Coordinates center, float radius, float radiusNoise, float velocity, float initAngle, int nrOfLines, Color color) {
        centerCoordinates = new Coordinates(center);
        lineEndpoints = new ArrayList<>();

        this.radius = radius;
        this.radiusNoise = radiusNoise;
        this.velocity = velocity;
        this.nrOfLines = nrOfLines;
        this.angle = initAngle;

        for(int i = 0; i < 2 * nrOfLines; ++i) {
            final float offsetAngle = (float) Math.toRadians(i * 180f / nrOfLines);
            final float x = centerCoordinates.getX() + radius * (float) Math.cos(offsetAngle);
            final float y = centerCoordinates.getY() + radius * (float) Math.sin(offsetAngle);

            lineEndpoints.add(new Coordinates(x, y));
        }
        for(int i = 0; i < 2 * nrOfLines; ++i) {
            final Coordinates endP1 = lineEndpoints.get(i);
            final Coordinates endP2 = lineEndpoints.get((i + 2) % (2 * nrOfLines));

            shapesList.add(new ShapeLine(endP1.getX(), endP1.getY(), endP2.getX(), endP2.getY(), color));
        }

        shapesList.add(new ShapeCircle(center.getX(), center.getY(), radius, color));
        shapesList.add(new ShapeCircle(center.getX(), center.getY(), radius + 8, color));
    }

    @Override
    public Shape cloneComponent() {
        return null;
    }

    @Override
    public boolean add(Shape shape) {
        return false;
    }

    @Override
    public boolean addAll(Shape... shapes) {
        return false;
    }

    @Override
    public Shape remove(Shape shape) {
        return null;
    }

    @Override
    public List<Shape> removeAll() {
        return null;
    }

    @Override
    public void display(Graphics g) {
        final float noisyRadius = (float) (radius + radiusNoise * Math.cos(Math.toRadians(angle)));

        for(int i = 0; i < 2 * nrOfLines; i++) {
            final float offsetAngle = (float) Math.toRadians(angle + i * 180f / nrOfLines);
            final Coordinates endP = lineEndpoints.get(i);

            endP.setX((float) (centerCoordinates.getX() + noisyRadius * Math.cos(offsetAngle)));
            endP.setY((float) (centerCoordinates.getY() + noisyRadius * Math.sin(offsetAngle)));
        }

        for(int i = 0; i < 2 * nrOfLines; i++) {
            final ShapeLine line = (ShapeLine) getChild(i);
            final Coordinates endP1 = lineEndpoints.get(i);
            final Coordinates endP2 = lineEndpoints.get((i + 2) % (2 * nrOfLines));

            line.setCoordinates(endP1.getX(), endP1.getY());
            line.setEndCoordinates(endP2.getX(), endP2.getY());
        }

        ((ShapeCircle) getChild(2 * nrOfLines)).setRadius(noisyRadius);
        ((ShapeCircle) getChild(2 * nrOfLines + 1)).setRadius(noisyRadius + 8);

        getChild(2 * nrOfLines).setCoordinates(centerCoordinates);
        getChild(2 * nrOfLines + 1).setCoordinates(centerCoordinates);

        super.display(g);

        angle += velocity;
        angle %= 360;
    }

    @Override
    public void setCoordinates(float x, float y) {
        centerCoordinates.setCoordinates(x, y);
    }
}
