package com.mygdx.game.Event.System;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Actor.Abstract.Actor;

/**
 * Created by woojen on 2016-12-05.
 */
public class Elevator extends Actor {


    public Elevator(Texture textureFileName, float x, float y, int size) {
        super(textureFileName, x, y, size);
    }


    public Rectangle getElevatorRectangle() {
        return new Rectangle(
                sprite.getX(),
                sprite.getY(),
                sprite.getWidth(),
                sprite.getHeight());
    }

    public void startElevatorTop(){

        setSpeedY(-1);
    }

    public void startElevatorBottom(){

        setSpeedY(1);
    }



}
