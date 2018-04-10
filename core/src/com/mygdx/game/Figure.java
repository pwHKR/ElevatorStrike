package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by woojen on 2016-11-14.
 */
public class Figure extends Actor {

    private int Hp;

    public Figure(String textureFileName, float x, float y, int size, int Hp) {
        super(textureFileName, x, y, size);
        this.Hp = Hp;
    }

    public void SetHP(int HP) {
        this.Hp = HP;
    }

    public int GetHP() {
        return Hp;
    }


}

