package com.mygdx.game.Actor.Player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Actor.Abstract.Figure;

/**
 * Created by woojen on 2016-11-14.
 */
public class Player extends Figure {

    private boolean Jumping; // new parameters for player
    private boolean Ducking; // new parameters for player

    private Texture idleUpdateImage;


    private Texture playerAnimation1;
    private Texture playerAnimation2;
    private Texture playerAnimation3;
    private Texture playerAnimation4;
    private Texture playerAnimation5;
    private Texture playerAnimation6;
    private Texture playerAnimation7;
    private Texture playerAnimation8;
    private Texture playerAnimation9;

    private Texture playerJumpImage;
    private Texture playerDuckImage;

    public Player(Texture textureFileName, float x, float y, int size, int Hp, boolean Jumping, boolean Ducking, Texture idleUpdateImage,
                  Texture playerAnimation1, Texture playerAnimation2, Texture playerAnimation3, Texture playerAnimation4,
                  Texture playerAnimation5, Texture playerAnimation6, Texture playerAnimation7, Texture playerAnimation8,
                  Texture playerAnimation9, Texture playerJumpImage, Texture playerDuckImage) {
        super(textureFileName, x, y, size, Hp);
        getSprite().setOriginCenter();
        this.idleUpdateImage = idleUpdateImage;

        this.playerAnimation1 = playerAnimation1;
        this.playerAnimation2 = playerAnimation2;
        this.playerAnimation3 = playerAnimation3;
        this.playerAnimation4 = playerAnimation4;
        this.playerAnimation5 = playerAnimation5;
        this.playerAnimation6 = playerAnimation6;
        this.playerAnimation7 = playerAnimation7;
        this.playerAnimation8 = playerAnimation8;
        this.playerAnimation9 = playerAnimation9;

        this.playerJumpImage = playerJumpImage;
        this.playerDuckImage = playerDuckImage;
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
            updateImage(playerAnimation1);

        }
        if (animationCounter >= 0.24f && animationCounter <= 0.25f) {
            updateImage(playerAnimation2);

        }
        if (animationCounter >= 0.36f && animationCounter <= 0.37f) {
            updateImage(playerAnimation3);

        }
        if (animationCounter >= 0.48f && animationCounter <= 0.49f) {
            updateImage(playerAnimation4);

        }
        if (animationCounter >= 0.60f && animationCounter <= 0.61f) {
            updateImage(playerAnimation5);

        }
        if (animationCounter >= 0.82f && animationCounter <= 0.83f) {
            updateImage(playerAnimation6);

        }
        if (animationCounter >= 0.94f && animationCounter <= 0.95f) {
            updateImage(playerAnimation7);

        }
        if (animationCounter >= 1.05f && animationCounter <= 1.06f) {
            updateImage(playerAnimation8);

        }
        if (animationCounter >= 1.17f && animationCounter <= 1.18f) {
            updateImage(playerAnimation9);

        }

        float animationStop = animationCounter;

        if (animationCounter > 1.19f) {
            return true;
        } else


            return false;
    }

    public boolean idleImage() {
        updateImage(idleUpdateImage);
        return true;
    }

    public void jumpImage() {

        if (Jumping == true) {

            updateImage(playerJumpImage);
        }


    }

    public void duckImage() {
        if (Ducking == true) {
            updateImage(playerDuckImage);

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


