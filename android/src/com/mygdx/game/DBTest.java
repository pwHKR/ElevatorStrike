package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DB.PosStorage;
import DB.Multiplayer;

/**
 * Created by woojen on 2018-04-20.
 */

public class DBTest extends Thread implements Multiplayer {

    private Connection connection;

    private final String connectionURL;

    private String dbName="EStrike";
    private String user = "stefan";
    private  String password="kameler1337";
    private int id; // Test variable

    private boolean isPlayer1; // Only used for testing multiplayer in early stage of development.
                                 // set this to true on one device and false on the other device


    private PosStorage posStorage;



    // TODO: 2018-04-30 Implementera databas

   public DBTest()  {

       isPlayer1 = false; // Used for Testing. See Comment in variable decleration



       connectionURL = "jdbc:mysql://206.189.50.69:3306/" + dbName + "?user=" + user + "&password=" + password + "&useSSL=false";


       }

       public void testDB(){








        /* connection = DriverManager.getConnection("jdbc:mysql://206.189.50.69:3306/EStrike?autoReconnect=true&useSSL=false&" +
                "user=stefan&password=kameler1337"); */


           try {
               Class.forName("com.mysql.jdbc.Driver");
               connection = DriverManager.getConnection(connectionURL);
           } catch (ClassNotFoundException e) {

               Gdx.app.log("classFail","Class not found" );
               e.printStackTrace();
           }

           catch (SQLException e) {

               Gdx.app.log("sqlFail", "check stacktrace" );
               e.printStackTrace();
           }



           if(connection == null){

               Gdx.app.log("failc","Connection failed" );
           }

           else {Gdx.app.log("okc","Connection succses" );}
       }

    @Override
    public void run() {

           testDB();


     //   Gdx.app.log("nameR"," Rooms: "+getRoomNames().get(0));

        /*

        Gdx.app.log("ennemyXpos",String.valueOf(getEnemyPositionX()));
        Gdx.app.log("ennemyYpos",String.valueOf(getEnemyPositionY()));

        setPlayerPosition(18,19); */



        while(true){



            posStorage = PosStorage.getInstance();

            if(posStorage.isUpdate()) {

                setPlayerPosition(posStorage.getPlayerPosX_DB(),posStorage.getPlayerPosY_DB());

                posStorage.setEnemyPosX_DB(getEnemyPositionX());
                posStorage.setEnemyPosY_DB(getEnemyPositionY());

            }
        }
    }


    public ArrayList<String> getRoomNames() {

      ArrayList<String> roomName = new ArrayList<String>();



        try  {



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

           if(isPlayer1){id = 1;}
           else {id = 2;}

           String query = "UPDATE `Player` SET `XPos`="+xInt+",`YPos`="+yInt+" where id="+ id;

        try  {

            Statement stmt = connection.createStatement();

            stmt.executeUpdate(query);

        } catch(Exception e){}

    }

    @Override
    public float getEnemyPositionX() {



        if(isPlayer1){id = 2;}
        else {id = 1;}

        int positionX = 0;

      String query ="SELECT Xpos FROM `Player` WHERE id ="+id;

        try{

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);


            while (rs.next()) {

                positionX = rs.getInt("Xpos");

            }

        }catch(Exception e){}

        return positionX;

    }

    @Override
    public float getEnemyPositionY() {


        if(isPlayer1){id = 2;}
        else {id = 1;}

        int positionY = 0;

        String query ="SELECT Ypos FROM `Player` WHERE id ="+id;



        try{

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);


            while (rs.next()) {

                positionY = rs.getInt("Ypos");

            }

        }catch(Exception e){}

        return positionY;




    }

    @Override
    public void setPlayerBullet(float x, float y) {

    }

    @Override
    public void setEnemyBullet(float x, float y) {

    }


}
