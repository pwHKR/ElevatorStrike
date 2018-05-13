package com.mygdx.game.Actor.Abstract;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Actor.Abstract.Actor;

/**
 * Created by woojen on 2016-11-14.
 */
public abstract class Figure extends Actor {

    private int Hp;

    public Figure(Texture textureFileName, float x, float y, int size, int Hp) {
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

