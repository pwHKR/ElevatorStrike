package com.mygdx.game.Item;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Level.System.Obstacle;

/**
 * Created by woojen on 2017-01-02.
 */
public class Item extends Obstacle {

    public Item(Texture textureFileName, float x, float y, int width, int height) {
        super(textureFileName, x, y, width, height);
    }
}
