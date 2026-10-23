package org.bullethell.game.ui.components.background;

import org.bullethell.game.logic.game.GameManager;

import java.awt.*;
import java.awt.geom.Point2D;

public final class Gradient {
    private final GameManager gameManager;

    private final Point2D startPoint, endPoint;
    private Color startColor, endColor;

    public Gradient(GameManager gameManager) {
        this(
                gameManager,
                new Point(0, 0),
                new Point(gameManager.getPlayfieldWidth(), gameManager.getPlayfieldHeight()),
                Color.BLACK,
                Color.BLACK
        );
    }

    public Gradient(GameManager gameManager, Point2D startPoint, Point2D endPoint, Color startColor, Color endColor) {
        this.gameManager = gameManager;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startColor = startColor;
        this.endColor = endColor;
    }

    public void display(Graphics g) {
        final GradientPaint gradient = new GradientPaint(startPoint, startColor, endPoint, endColor);
        final Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, gameManager.getPlayfieldWidth(), gameManager.getPlayfieldHeight());
    }

    public Point2D getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(int x, int y) {
        startPoint.setLocation(x, y);
    }

    public Point2D getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(int x, int y) {
        endPoint.setLocation(x, y);
    }

    public Color getStartColor() {
        return startColor;
    }

    public void setStartColor(Color startColor) {
        this.startColor = startColor;
    }

    public Color getEndColor() {
        return endColor;
    }

    public void setEndColor(Color endColor) {
        this.endColor = endColor;
    }

    public void clear() {
        startPoint.setLocation(0, 0);
        startColor = Color.BLACK;
        endPoint.setLocation(gameManager.getPlayfieldWidth(), gameManager.getPlayfieldHeight());
        endColor = Color.BLACK;
    }
}
