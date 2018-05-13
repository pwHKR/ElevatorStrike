package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;


import com.badlogic.gdx.Gdx;
import com.mygdx.game.Level.LevelFactory;


import DB.PosStorage;


public class MyGdxGame extends ApplicationAdapter implements ApplicationListener {

    private LevelFactory levelFactory;
    private PosStorage renderPos;




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


      //  Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());



    }



    public void render() {







        levelFactory.testLevel();

        renderPos = PosStorage.getInstance();

        renderPos.setUpdate(true);



        if(renderPos.isEndGameEnemy()){


            //Gdx.app.exit();


        }

        if(renderPos.isEndGamePlayer()){

            renderPos.setEndGamePlayer(true);


        }






    }




    @Override
    public void dispose() {

        levelFactory.dispose();




}}

