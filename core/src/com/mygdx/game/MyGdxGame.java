package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.Level.LevelFactory;


import DB.PosStorage;


public class MyGdxGame extends Game {

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






        setScreen(new SplashScreen(this));


        //Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());



    }



    public void render() {


        super.render();


        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        renderPos = PosStorage.getInstance();

        renderPos.setUpdate(true);

        Gdx.app.log("endGame",String.valueOf(renderPos.isEndGameEnemy()));

        if(renderPos.isEndGameEnemy()){




            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            renderPos.setResetEndGame(true);
            Gdx.app.exit();


        }

        if(renderPos.isEndGamePlayer()){

            renderPos.setEndGamePlayer(true);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while(true){

                if (renderPos.isResetEndGame()){
            Gdx.app.exit();}}
        }

    }

    @Override
    public void dispose() {
        super.dispose();

    }
}

