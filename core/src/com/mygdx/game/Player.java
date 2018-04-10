package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by woojen on 2016-11-14.
 */
public class Player extends Figure {

    private boolean Jumping; // new parameters for player
    private boolean Ducking; // new parameters for player

    public Player(String textureFileName, float x, float y, int size, int Hp, boolean Jumping, boolean Ducking) {
        super(textureFileName, x, y, size, Hp);
        getSprite().setOriginCenter();
    }

    public void updatePositionFromSpeed() {
        //First call the method "updatePositionFromSpeed" in the Actor super class
        super.updatePositionFromSpeed();
    }


    public void goLeft() {


        // walking x speed
        if (getSpeedY() == 0 && Jumping == false) {
            sprite.translateX(-2f);
        }

        // jumping x speed
        else if (Jumping == true) {
            sprite.translateX(-3f);
        }

        //falling xspeed
        else if (getSpeedY() != 0 && Jumping == false) {
            sprite.translateX(-3f);
        }

    }

    public void goRight() {

        // walking x speed speed
        if (getSpeedY() == 0 && Jumping == false) {
            sprite.translateX(2f);
        }

        // jumping x speed
        else if (Jumping == true) {

            sprite.translateX(3f);
        }

        // falling x speed
        else if (getSpeedY() != 0 && Jumping == false) {
            sprite.translateX(3f);
        }


    }

    public boolean animation(float animationCounter) {


        if (animationCounter >= 0.12f && animationCounter <= 0.13f) {
            updateImage("Run_Shoot__001.png");

        }
        if (animationCounter >= 0.24f && animationCounter <= 0.25f) {
            updateImage("Run_Shoot__002.png");

        }
        if (animationCounter >= 0.36f && animationCounter <= 0.37f) {
            updateImage("Run_Shoot__003.png");

        }
        if (animationCounter >= 0.48f && animationCounter <= 0.49f) {
            updateImage("Run_Shoot__004.png");

        }
        if (animationCounter >= 0.60f && animationCounter <= 0.61f) {
            updateImage("Run_Shoot__005.png");

        }
        if (animationCounter >= 0.82f && animationCounter <= 0.83f) {
            updateImage("Run_Shoot__006.png");

        }
        if (animationCounter >= 0.94f && animationCounter <= 0.95f) {
            updateImage("Run_Shoot__007.png");

        }
        if (animationCounter >= 1.05f && animationCounter <= 1.06f) {
            updateImage("Run_Shoot__008.png");

        }
        if (animationCounter >= 1.17f && animationCounter <= 1.18f) {
            updateImage("Run_Shoot__009.png");

        }

        float animationStop = animationCounter;

        if (animationCounter > 1.19f) {
            return true;
        } else


            return false;
    }

    public boolean idleImage() {
        updateImage("i1.png");
        return true;
    }

    public void jumpImage() {

        if (Jumping == true) {

            updateImage("j1.png");
        }


    }

    public void duckImage() {
        if (Ducking == true) {
            updateImage("lay.png");

        }


    }

    public Rectangle getPlayerRectangle() {
        return new Rectangle(
                sprite.getX(),
                sprite.getY(),
                sprite.getWidth(),
                sprite.getHeight());
    }

    // These to methods are for switching the Jumping boolean on or off

    public void jumpSwitchOn() {

        Jumping = true;
    }

    public void jumpSwitchOff() {

        Jumping = false;
    }


    public void duckSwitchOn() {
        Ducking = true;
    }

    public void duckSwitchOff() {
        Ducking = false;
    }


}


