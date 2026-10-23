package org.bullethell.game.graphics.components.shapes.composite;

import org.bullethell.game.graphics.components.shapes.Shape;

import java.awt.*;
import java.util.*;
import java.util.List;

public class CompositeShape implements Shape {
    protected final ArrayList<Shape> shapesList = new ArrayList<>();

    public CompositeShape(Shape... shapes) {
        shapesList.addAll(Arrays.asList(shapes));
    }

    @Override
    public Shape cloneComponent() {
        return new CompositeShape(cloneShapesList().toArray(new Shape[0]));
    }

    @Override
    public float getX() {
        return shapesList.stream()
                .map(Shape::getX)
                .min(Float::compare)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public float getY() {
        return shapesList.stream()
                .map(Shape::getY)
                .min(Float::compare)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public float getWidth() {
        final float compositeX = getX();
        return shapesList.stream()
                .map(shape -> (shape.getX() - compositeX + shape.getWidth()))
                .max(Float::compare)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public float getHeight() {
        final float compositeY = getY();
        return shapesList.stream()
                .map(shape -> (shape.getY() - compositeY + shape.getHeight()))
                .max(Float::compare)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void moveComponent(float x, float y) {
        shapesList.forEach(shape -> shape.moveComponent(x, y));
    }

    @Override
    public void setCoordinates(float x, float y) {
        final float compositeX = getX(), compositeY = getY();
        shapesList.forEach(shape -> shape.setCoordinates(
                shape.getX() + x - compositeX,
                shape.getY() + y - compositeY
            )
        );
    }

    @Override
    public void display(Graphics g) {
        shapesList.forEach(shape -> shape.display(g));
    }

    @Override
    public Color getColor() {
        return shapesList.getFirst().getColor();
    }

    @Override
    public void setColor(Color color) {
        shapesList.forEach(shape -> shape.setColor(color));
    }

    public boolean add(Shape shape) {
        return shapesList.add(shape);
    }

    public boolean addAll(Shape... shapes) {
        return shapesList.addAll(Arrays.asList(shapes));
    }

    public Shape remove(Shape shape) {
        return (shapesList.remove(shape) ? shape : null);
    }

    public List<Shape> removeAll() {
        final List<Shape> returnedList = new ArrayList<>();
        for(Iterator<Shape> it = shapesList.iterator(); it.hasNext();) {
            final Shape shape = it.next();
            returnedList.add(shape);
            it.remove();
        }

        return returnedList;
    }

    public void clear() {
        shapesList.clear();
    }

    public Shape getChild(int index) {
        if(index < 0 || index >= shapesList.size()) {
            return null;
        }

        return shapesList.get(index);
    }

    protected final List<Shape> cloneShapesList() {
        final List<Shape> newShapesList = new ArrayList<>();
        for (Shape shape : shapesList) {
            newShapesList.add((Shape) shape.cloneComponent());
        }

        return newShapesList;
    }
}
