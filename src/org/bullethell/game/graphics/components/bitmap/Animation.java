package org.bullethell.game.graphics.components.bitmap;

import org.bullethell.game.graphics.components.bitmap.utils.SpriteCache;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public final class Animation implements Bitmap {
    private final String animationKey;
    private final java.util.List<Sprite> frames;
    private final int numberOfFrames, animationRate;
    private final boolean isLooped;

    private int currentFrame, animationTimer;
    private boolean hasAnimationFinished;

    public Animation(String animationKey) {
        this(animationKey, 5, false);
    }

    public Animation(String animationKey, int animationRate) {
        this(animationKey, animationRate, false);
    }

    public Animation(String animationKey, int animationRate, boolean isLooped) {
        this(new ArrayList<>(SpriteCache.getSpriteSequence(animationKey)), animationRate, isLooped);
    }

    public Animation(List<Sprite> frames) {
        this(frames, 5, false);
    }

    public Animation(List<Sprite> frames, int animationRate) {
        this(frames, animationRate, false);
    }

    public Animation(List<Sprite> frames, int animationRate, boolean isLooped) {
        this.frames = frames;
        this.animationRate = animationRate;
        this.isLooped = isLooped;

        animationKey = frames.getFirst().getKey().replaceAll("_\\d+$", "");
        numberOfFrames = frames.size();
    }

    @Override
    public Bitmap cloneComponent() {
        return new Animation(frames, animationRate, isLooped);
    }

    @Override
    public String getKey() {
        return animationKey;
    }

    @Override
    public BufferedImage getImage() {
        return frames.get(currentFrame).getImage();
    }

    @Override
    public boolean isDirty() {
        return frames.get(currentFrame).isDirty();
    }

    @Override
    public void setAngle(float angle) {
        frames.get(currentFrame).setAngle(angle);
    }

    @Override
    public void setScale(float scale) {
        frames.get(currentFrame).setScale(scale);
    }

    @Override
    public float getX() {
        return frames.get(currentFrame).getX();
    }

    @Override
    public float getY() {
        return frames.get(currentFrame).getY();
    }

    @Override
    public float getWidth() {
        return frames.stream()
                .map(Sprite::getWidth)
                .max(Float::compare)
                .orElse(0f);
    }

    @Override
    public float getHeight() {
        return frames.stream()
                .map(Sprite::getHeight)
                .max(Float::compare)
                .orElse(0f);
    }

    @Override
    public void moveComponent(float x, float y) {
        frames.forEach(sprite -> sprite.moveComponent(x, y));
    }

    @Override
    public void setCoordinates(float x, float y) {
        frames.forEach(sprite -> sprite.setCoordinates(x, y));
    }

    @Override
    public void display(Graphics g) {
        animate();
        if(!isAnimationFinished()) {
            frames.get(currentFrame).display(g);
        }
    }

    private void animate() {
        if(animationTimer >= animationRate) {
            animationTimer = 0;

            if(currentFrame < numberOfFrames) {
                currentFrame++;
            }
        } else {
            animationTimer++;
        }

        if(currentFrame >= numberOfFrames) {
            if(isLooped) {
                currentFrame = 0;
            }
            else {
                hasAnimationFinished = true;
            }
        }
    }

    public boolean isAnimationFinished() {
        return hasAnimationFinished;
    }
}
