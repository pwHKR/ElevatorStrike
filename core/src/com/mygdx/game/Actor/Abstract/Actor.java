package com.mygdx.game.Actor.Abstract;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

public abstract class Actor  {

    private int speedX = 0;
    private int speedY = 0;
    public Sprite sprite;
    private final int SHRINK_COLLISION_RADIUS;

    public Actor(Texture textureFileName, float x, float y, int size) {
        sprite = new Sprite(textureFileName);

        sprite.setSize(size, size);
        sprite.setX(x);
        sprite.setY(y);
        SHRINK_COLLISION_RADIUS = size / 16;
    }


    public void updateImage(Texture filename) {
        sprite.setTexture(filename);
    }


    public float getX() {
        return sprite.getX();
    }

    public void setX(float x) {
        sprite.setX(x);
    }

    public float getY() {
        return sprite.getY();
    }

    public void setY(float y) {
        sprite.setY(y);
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int xSpeed) {
        this.speedX = xSpeed;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int ySpeed) {
        this.speedY = ySpeed;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void updatePositionFromSpeed() {
        if (getSpeedX() == 0 && getSpeedY() == 0)
            return;
        setX(getX() + getSpeedX());
        setY(getY() + getSpeedY());

        //What should we do when we get to the screen edge? Bounce or stop?
        //bounceAtEdge();
        //stopAtEdge();
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    //If we are at the screen egde...
    //...change speed to opposite direction ("bounce").
    public void bounceAtEdge() {
        if ((getX() > Gdx.graphics.getWidth() - sprite.getWidth()) || (getX() < 0))
            setSpeedX(-getSpeedX());
        if ((getY() > Gdx.graphics.getHeight() - sprite.getHeight()) || (getY() < 0))
            setSpeedY(-getSpeedY());
    }

    public void stopAtEdge() {
        if (getX() > Gdx.graphics.getWidth() - sprite.getWidth())
            setX(Gdx.graphics.getWidth() - sprite.getWidth());
        if (getX() < 0)
            setX(0);
        if (getY() > Gdx.graphics.getHeight() - sprite.getHeight())
            setY(Gdx.graphics.getHeight() - sprite.getHeight());
        if (getY() < 0)
            setY(0);
    }
    /*
        Check if two figures collide.
        To check if they collide...
        1. Convert their sprite positions and sizes to rectangles.
        2. Check if the rectangles overlap
     */
    public boolean collidesWith(Actor otherFigure) {
        Rectangle rect1 = new Rectangle(
                getSprite().getX() + SHRINK_COLLISION_RADIUS,
                getSprite().getY() + SHRINK_COLLISION_RADIUS,
                getSprite().getWidth() - (2 * SHRINK_COLLISION_RADIUS),
                getSprite().getHeight() - (2 * SHRINK_COLLISION_RADIUS));
        Rectangle rect2 = new Rectangle(
                otherFigure.getSprite().getX() + SHRINK_COLLISION_RADIUS,
                otherFigure.getSprite().getY() + SHRINK_COLLISION_RADIUS,
                otherFigure.getSprite().getWidth() - (2 * SHRINK_COLLISION_RADIUS),
                otherFigure.getSprite().getHeight() - (2 * SHRINK_COLLISION_RADIUS));
        return rect1.overlaps(rect2);
    }

}


