package com.mygdx.game.Item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Item.Item;

/**
 * Created by woojen on 2016-12-07.
 */
public class Key extends Item {

    private boolean hasKey = false;

    public Key(Texture textureFileName, float x, float y, int width, int height) {
        super(textureFileName, x, y, width, height);


    }

    public void setKeyTrue() {
        this.hasKey = true;
    }

    public boolean getHasKey() {
        return hasKey;
    }

    public Rectangle getKeyRectangle() {
        return new Rectangle(
                sprite.getX(),
                sprite.getY(),
                sprite.getWidth(),
                sprite.getHeight());
    }
}
