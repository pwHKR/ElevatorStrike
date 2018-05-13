package DB;

/**
 * Created by woojen on 2018-04-19.
 * <p>
 * Interface for connecting the game classes with the Database classes
 */

public interface Multiplayer {

    // Player Position

    void setPlayerPosition(float x, float y);


    // Enemy Position

    float getEnemyPositionX();

    float getEnemyPositionY();


    // Elevator Sync

    boolean getInSyncEnemy();

    void setInSync(boolean inSync);


    //PlayerBullet


    void setBulletPosition(float x, float y, boolean isLeft);
    void newBullet(boolean newSendBullet);


    // get enemy bullets position

    float getBulletPositionX();

    float getBulletPositionY();

    boolean getNewBulletEnemy();

    boolean getEnemyBulletDirection();


    // Ack enemy bullet

    void receivedEnemyBullet();








}
