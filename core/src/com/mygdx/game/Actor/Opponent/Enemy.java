package com.mygdx.game.Actor.Opponent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Actor.Abstract.Figure;


/**
 * Created by woojen on 2016-11-14.
 */
public class Enemy extends Figure {

    public Enemy(Texture textureFileName, float x, float y, int size, int Hp) {
        super(textureFileName, x, y, size, Hp);
    }

    public void updatePositionFromSpeed() {

        super.updatePositionFromSpeed();

        bounceAtEdge();
    }

    public Rectangle getEnemieRectangle() {
        return new Rectangle(
                sprite.getX(),
                sprite.getY(),
                sprite.getWidth(),
                sprite.getHeight());
    }


    /*

    public boolean animation(float animationCounter) {


        if (animationCounter >= 0.12f && animationCounter <= 0.13f) {
            updateImage("r1.png");

        }
        if (animationCounter >= 0.24f && animationCounter <= 0.25f) {
            updateImage("r2.png");

        }
        if (animationCounter >= 0.36f && animationCounter <= 0.37f) {
            updateImage("r3.png");

        }
        if (animationCounter >= 0.48f && animationCounter <= 0.49f) {
            updateImage("r4.png");

        }
        if (animationCounter >= 0.60f && animationCounter <= 0.61f) {
            updateImage("r5.png");


        }
        if (animationCounter >= 0.82f && animationCounter <= 0.83f) {
            updateImage("r6.png");

        }

        float animationStop = animationCounter;

        if (animationCounter > 0.83f) {
            return true;
        } else


            return false;
    } */

    /*

    public boolean idleImage() {
        updateImage("i1e.png");
        return true;
    }
*/

}



