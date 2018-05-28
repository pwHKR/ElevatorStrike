package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DB.Multiplayer;
import DB.PosStorage;

/**
 * Created by woojen on 2018-04-20.
 */

public class DBTest extends Thread implements Multiplayer {

    private Connection connection;

    private final String connectionURL;

    private String dbName = "EStrike";
    private String user = "stefan";
    private String password = "kameler1337";
    private int id; // Test variable

    private boolean isPlayer1; // Only used for testing multiplayer in early stage of development.
    // set this to true on one device and false on the other device


    private PosStorage posStorage;


    // TODO: 2018-04-30 Implementera databas

    public DBTest() {

       // Used for Testing. See Comment in variable decleration


        connectionURL = "jdbc:mysql://206.189.50.69:3306/" + dbName + "?user=" + user + "&password=" + password + "&useSSL=false";






    }



    public void testDB() {


        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionURL);
        } catch (ClassNotFoundException e) {

            Gdx.app.log("classFail", "Class not found");
            e.printStackTrace();
        } catch (SQLException e) {

            Gdx.app.log("sqlFail", "check stacktrace");
            e.printStackTrace();
        }


        if (connection == null) {

            Gdx.app.log("failc", "Connection failed");
        } else {
            Gdx.app.log("okc", "Connection succses");
        }


    }

    @Override
    public void run() {




        testDB();



        //endGameReset();

        setPlayerID();

       // setReady(false);




        while (true) {

            posStorage = PosStorage.getInstance();





            if(posStorage.isUpdateSplashScreen()){

                //setReady(posStorage.isStartGame());


                //posStorage.setStartGameEnemy(getReadyEnemy());


                posStorage.setFull(isFull());






            }



            if (posStorage.isUpdate()) {



                posStorage.setEndGameEnemy(getEndGameEnemy());

                setPlayerPosition(posStorage.getPlayerPosX_DB(), posStorage.getPlayerPosY_DB());

                posStorage.setEnemyPosX_DB(getEnemyPositionX());
                posStorage.setEnemyPosY_DB(getEnemyPositionY());

                posStorage.setNewEnemyBullet(getNewBulletEnemy());

                if (posStorage.isNewPlayerBullet()) {

                    newBullet(true);

                    setBulletPosition(posStorage.getPlayerBulletX_DB(), posStorage.getPlayerBulletY_DB(), posStorage.isPlayerBulletDirecton_DB());

                    posStorage.setNewPlayerBullet(false);


                }

                if (posStorage.isNewEnemyBullet()) {

                    posStorage.setEnemyBulletX_DB(getBulletPositionX());
                    posStorage.setEnemyBulletY_DB(getBulletPositionY());
                    posStorage.setEnemyBulletDirecton_DB(getEnemyBulletDirection());

                    posStorage.setNewEnemyBullet(false);
                    receivedEnemyBullet();


                }

                if (posStorage.isEndGamePlayer()){

                    setEndGamePlayer(true);
                    isTakenReset();
                    endGameReset();
                    // set end game to db

                    posStorage.setResetEndGame(true);

                }

                posStorage.setEndGameEnemy(getEndGameEnemy());



            }


        }


    }


    public ArrayList<String> getRoomNames() {

        ArrayList<String> roomName = new ArrayList<String>();


        try {


            String query = "SELECT * FROM EStrike.Room";


            Statement stmt = connection.createStatement();
            stmt.addBatch(query);

            ResultSet rs = stmt.executeQuery(query);


            while (rs.next()) {

                roomName.add(rs.getString(1));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return roomName;


    }


    @Override
    public void setPlayerPosition(float x, float y) {

        int xInt = (int) x;
        int yInt = (int) y;

        if (isPlayer1) {
            id = 1;
        } else {
            id = 2;
        }

        String query = "UPDATE `Player` SET `XPos`=" + xInt + ",`YPos`=" + yInt + " where id=" + id;

        try {

            Statement stmt = connection.createStatement();

            stmt.executeUpdate(query);

        } catch (Exception e) {
        }

    }

    @Override
    public float getEnemyPositionX() {


        if (isPlayer1) {
            id = 2;
        } else {
            id = 1;
        }

        int positionX = 0;

        String query = "SELECT Xpos FROM `Player` WHERE id =" + id;

        try {

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);


            while (rs.next()) {

                positionX = rs.getInt("Xpos");

            }

        } catch (Exception e) {
        }

        return positionX;

    }

    @Override
    public float getEnemyPositionY() {


        if (isPlayer1) {
            id = 2;
        } else {
            id = 1;
        }

        int positionY = 0;

        String query = "SELECT Ypos FROM `Player` WHERE id =" + id;


        try {

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);


            while (rs.next()) {

                positionY = rs.getInt("Ypos");

            }

        } catch (Exception e) {
        }

        return positionY;


    }


    @Override
    public boolean getInSyncEnemy() {

        boolean inSync = false;


        if (isPlayer1) {
            id = 2;
        } else {
            id = 1;
        }

        int positionY = 0;

        String query = "SELECT inSync FROM `Player` WHERE id =" + id;


        try {

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);


            while (rs.next()) {

                inSync = rs.getBoolean("inSync");


            }

        } catch (Exception e) {
        }

        return inSync;


    }

    @Override
    public void setInSync(boolean inSync) {

        int tinyInt;

        if (inSync) {
            tinyInt = 1;
        } else {
            tinyInt = 0;
        }


        if (isPlayer1) {
            id = 1;
        } else {
            id = 2;
        }

        String query = "UPDATE `Player` SET `inSync`=" + tinyInt + " where id=" + id;

        try {

            Statement stmt = connection.createStatement();

            stmt.executeUpdate(query);

        } catch (Exception e) {
        }


    }

    @Override
    public void setBulletPosition(float x, float y, boolean isLeft) {

        int tinyInt;
        int xInt = (int) x;
        int yInt = (int) y;

        if (isPlayer1) {
            id = 1;
        } else {
            id = 2;
        }

        if (isLeft) {
            tinyInt = 1;
        } else {
            tinyInt = 0;
        }


        String query = "UPDATE `Player` SET `sendBulletX`=" + xInt + ",`sendBulletY`=" + yInt + ",`bulletDirection`=" + tinyInt + " where id=" + id;

        try {

            Statement stmt = connection.createStatement();

            stmt.executeUpdate(query);

        } catch (Exception e) {
        }

    }


    @Override
    public float getBulletPositionX() {


        if (isPlayer1) {
            id = 2;
        } else {
            id = 1;
        }

        int positionX = 0;

        String query = "SELECT sendBulletX FROM `Player` WHERE id =" + id;


        try {

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);


            while (rs.next()) {

                positionX = rs.getInt("sendBulletX");

            }

        } catch (Exception e) {
        }

        return positionX;


    }


    @Override
    public float getBulletPositionY() {


        if (isPlayer1) {
            id = 2;
        } else {
            id = 1;
        }

        int positionY = 0;

        String query = "SELECT sendBulletY FROM `Player` WHERE id =" + id;


        try {

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);


            while (rs.next()) {

                positionY = rs.getInt("sendBulletY");

            }

        } catch (Exception e) {
        }

        return positionY;

    }


    @Override


    public void newBullet(boolean newSendBullet) {

        int tinyInt;

        if (newSendBullet) {
            tinyInt = 1;
        } else {
            tinyInt = 0;
        }


        if (isPlayer1) {
            id = 1;
        } else {
            id = 2;
        }

        String query = "UPDATE `Player` SET `newBullet`=" + tinyInt + " where id=" + id;

        try {

            Statement stmt = connection.createStatement();

            stmt.executeUpdate(query);

        } catch (Exception e) {
        }


    }

    public void receivedEnemyBullet() {


        int tinyInt = 0; // always false


        if (isPlayer1) {
            id = 2;
        } else {
            id = 1;
        }

        String query = "UPDATE `Player` SET `newBullet`=" + tinyInt + " where id=" + id;

        try {

            Statement stmt = connection.createStatement();

            stmt.executeUpdate(query);

        } catch (Exception e) {
        }


    }


    @Override
    public boolean getNewBulletEnemy() {
        boolean newEnemyBullet = false;


        if (isPlayer1) {
            id = 2;
        } else {
            id = 1;
        }


        String query = "SELECT newBullet FROM `Player` WHERE id =" + id;


        try {

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);


            while (rs.next()) {

                newEnemyBullet = rs.getBoolean("newBullet");


            }

        } catch (Exception e) {
        }

        return newEnemyBullet;

    }


    @Override
    public boolean getEnemyBulletDirection() {
        boolean BulletDirection = false;


        if (isPlayer1) {
            id = 2;
        } else {
            id = 1;
        }


        String query = "SELECT bulletDirection FROM `Player` WHERE id =" + id;


        try {

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);


            while (rs.next()) {

                BulletDirection = rs.getBoolean("bulletDirection");


            }

        } catch (Exception e) {
        }

        return BulletDirection;

    }



    public boolean getEndGameEnemy() {
        boolean result = false;


        if (isPlayer1) {
            id = 2;
        } else {
            id = 1;
        }


        String query = "SELECT endGame FROM `Player` WHERE id =" + id;


        try {

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);


            while (rs.next()) {

                result = rs.getBoolean("endGame");


            }

        } catch (Exception e) {
        }

        return result;

    }


    public void setEndGamePlayer(boolean endGame) {

        int tinyInt;

        if (endGame) {
            tinyInt = 1;
        } else {
            tinyInt = 0;
        }


        if (isPlayer1) {
            id = 1;
        } else {
            id = 2;
        }

        String query = "UPDATE `Player` SET `endGame`=" + tinyInt + " where id=" + id;

        try {

            Statement stmt = connection.createStatement();

            stmt.executeUpdate(query);

        } catch (Exception e) {
        }


    }

    public void endGameReset() {



        String query = "UPDATE `Player` SET `endGame`= 0 where id > 0";

        try {

            Statement stmt = connection.createStatement();

            stmt.executeUpdate(query);

        } catch (Exception e) {
        }




    }

    public void isTakenReset() {



        String query = "UPDATE `Player` SET `isTaken`= 0 where id > 0";

        try {

            Statement stmt = connection.createStatement();

            stmt.executeUpdate(query);

        } catch (Exception e) {
        }




    }

    public void setReady(boolean ready) {

        int tinyInt;

        if (ready) {
            tinyInt = 1;
        } else {
            tinyInt = 0;
        }


        if (isPlayer1) {
            id = 1;

        } else {
            id = 2;


        }




        String query = "UPDATE `Player` SET `StartGame`=" + tinyInt + " where id=" + id;

        try {

            Statement stmt = connection.createStatement();

            stmt.executeUpdate(query);

        } catch (Exception e) {
        }


    }



    public void setIsTaken(boolean taken, int id) {

        int tinyInt;

        if (taken) {
            tinyInt = 1;
        } else {
            tinyInt = 0;
        }





        String query = "UPDATE `Player` SET `isTaken`=" + tinyInt + " where id=" + id;

        try {

            Statement stmt = connection.createStatement();

            stmt.executeUpdate(query);

        } catch (Exception e) {
        }


    }


    public boolean getReadyEnemy() {

        boolean ready = false;


        if (isPlayer1) {
            id = 1;
        } else {
            id = 2;
        }

        int positionY = 0;

        String query = "SELECT StartGame FROM `Player` WHERE id =" + id;


        try {

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);


            while (rs.next()) {

                ready = rs.getBoolean("StartGame");


            }

        } catch (Exception e) {
        }

        return ready;


    }


    public void setPlayerID() {

        int idFromDB = 0;












        String query = "SELECT Count(isTaken) FROM `Player` WHERE isTaken = 1";




        try {

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);



            while (rs.next()) {

                idFromDB = rs.getInt("Count(isTaken)");


            }

        } catch (Exception e) {

        }




        Gdx.app.log("idINT",String.valueOf(idFromDB));

        posStorage = PosStorage.getInstance();

        if (idFromDB == 0){

            setIsTaken(true,1);


            isPlayer1 = true;
            posStorage.setPlayer1(true);


        }


        if (idFromDB == 1){

            setIsTaken(true,2);
            isPlayer1 = false;
            posStorage.setPlayer1(false);

        }

        if (idFromDB >= 2){

            Gdx.app.log("isfull","Game is full");
        }



    }

    public boolean isFull(){

        int countFromDB = 0;
        boolean result;

        String query = "SELECT Count(isTaken) FROM `Player` WHERE isTaken = 1";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);



            while (rs.next()) {

                countFromDB = rs.getInt("Count(isTaken)");


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        if(countFromDB == 2){

            result = true;

        }

        else{result = false;}


        return  result;

}




}