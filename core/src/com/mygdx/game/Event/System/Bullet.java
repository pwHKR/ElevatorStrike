package com.mygdx.game.Event.System;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.Actor.Abstract.Actor;

/**
 * Created by Rasmus on 2016-11-20.
 */

public class Bullet extends Actor  {
    public Bullet(Texture textureFileName, float x, float y, int size) {
        super(textureFileName, x, y, size);
    }

    public void goLeft() {
        sprite.translateX(-10f);

    }

    public void goRight() {
        sprite.translateX(1f);
    }

    public void updatePositionFromSpeed() {
        //First call the method "updatePositionFromSpeed" in the Actor super class
        super.updatePositionFromSpeed();
    }

    public Rectangle getBulletRectangle() {
        return new Rectangle(
                sprite.getX(),
                sprite.getY(),
                sprite.getWidth(),
                sprite.getHeight());
    }



}
