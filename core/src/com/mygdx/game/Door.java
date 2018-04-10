package com.mygdx.game;

/**
 * Created by Peter on 2017-1-2.
 */
public class Door extends Obstacle {


    public Door(String textureFileName, float x, float y, int width, int height) {
        super(textureFileName, x, y, width, height);


    }

    public float getX() {return sprite.getX();}

    public float getY() {return sprite.getY();}

}
