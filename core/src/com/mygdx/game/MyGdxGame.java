package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.mygdx.game.Actor.Opponent.Enemy;
import com.mygdx.game.Event.System.Bullet;
import com.mygdx.game.Event.System.Door;
import com.mygdx.game.Actor.Player.Player;
import com.mygdx.game.Event.System.Elevator;
import com.mygdx.game.Event.System.Explossion;
import com.mygdx.game.Item.HealthPack;
import com.mygdx.game.Item.Key;
import com.mygdx.game.Level.LevelFactory;
import com.mygdx.game.Level.System.Obstacle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import DB.Interface.DBHandler;


public class MyGdxGame extends ApplicationAdapter implements ApplicationListener {

    LevelFactory levelFactory;



    @Override
    public void create() {

        levelFactory = LevelFactory.getInstance();



    }




    public void render() {



        levelFactory.testLevel();


    }




    @Override
    public void dispose() {



}}

