package com.mygdx.game.Event.System;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Level.System.Obstacle;

import java.awt.TextArea;

/**
 * Created by Peter on 2017-1-2.
 */
public class Door extends Obstacle {


    public Door(Texture textureFileName, float x, float y, int width, int height) {
        super(textureFileName, x, y, width, height);


    }

    public float getX() {return sprite.getX();}

    public float getY() {return sprite.getY();}

}
