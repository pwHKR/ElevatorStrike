package DB.Interface;

import com.sun.tools.javac.comp.Todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by woojen on 2018-04-20.
 */

public class DBHandler implements Multiplayer {


    // TODO: 2018-04-30 Implementera databas 

    public DBHandler() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://206.189.50.69:3306/Elevator?autoReconnect=true&useSSL=false&" +
                "user=stefan&password=kameler1337");
    }


    @Override
    public void setPlayerPosition(float x, float y) {

    }

    @Override
    public float getEnemyPositionX() {
        return 300;
    }

    @Override
    public float getEnemyPositionY() {
        return 200;
    }

    @Override
    public void setPlayerBullet(float x, float y) {

    }

    @Override
    public void setEnemyBullet(float x, float y) {

    }
}
