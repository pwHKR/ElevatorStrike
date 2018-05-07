package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;

import com.mygdx.game.Level.LevelFactory;

import DB.PosStorage;


public class MyGdxGame extends ApplicationAdapter implements ApplicationListener {

    LevelFactory levelFactory;
    PosStorage enemyPos;




    @Override
    public void create() {

       /* try {
            dbh = DBHandler.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

 */

        //ArrayList<String> temp = new ArrayList<String>();






       // Gdx.app.log("Room names",dbh.getRoomNames().get(0));





        levelFactory = LevelFactory.getInstance();




    }




    public void render() {




        levelFactory.testLevel();

        enemyPos = PosStorage.getInstance();

        enemyPos.setUpdate(true);

    }




    @Override
    public void dispose() {



}}

