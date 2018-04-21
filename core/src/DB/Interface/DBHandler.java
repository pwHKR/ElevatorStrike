package DB.Interface;

/**
 * Created by woojen on 2018-04-20.
 */

public class DBHandler implements Multiplayer {

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
