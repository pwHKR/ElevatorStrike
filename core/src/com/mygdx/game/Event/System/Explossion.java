package com.mygdx.game.Event.System;

import com.mygdx.game.Actor.Abstract.Actor;

/**
 * Created by woojen on 2016-12-29.
 */
public class Explossion extends Actor {
    public Explossion(String textureFileName, float x, float y, int size) {
        super(textureFileName, x, y, size);
    }


    public boolean animation(float animationCounter) {



        if (animationCounter >= 0.12f && animationCounter <= 0.13f) {
            updateImage("MidAirExplo__001.png");

        }

        if (animationCounter >= 0.24f && animationCounter <= 0.25f) {
            updateImage("MidAirExplo__002.png");

        }

        if (animationCounter >= 0.36f && animationCounter <= 0.37f) {
            updateImage("MidAirExplo__003.png");

        }

        if (animationCounter >= 0.48f && animationCounter <= 0.49f) {
            updateImage("MidAirExplo__004.png");

        }

        if (animationCounter >= 0.60f && animationCounter <= 0.61f) {
            updateImage("MidAirExplo__005.png");


        }

        if (animationCounter >= 0.82f && animationCounter <= 0.83f) {
            updateImage("MidAirExplo__006.png");
        }


        if (animationCounter >= 1f && animationCounter <= 1.01f) {
            updateImage("MidAirExplo__007.png");


        }


        if (animationCounter >= 1.2f && animationCounter <= 1.21f) {
            updateImage("MidAirExplo__008.png");


        }

        if (animationCounter > 1.21f) {
            return true;
        }

        else


            System.out.println(animationCounter);
        return false;
    }


    }







