package DB;

/**
 * Created by woojen on 2018-04-19.
 *
 * Interface for connecting the game classes with the Database classes
 */

public interface Multiplayer {

    // Player Position

    void setPlayerPosition(float x,float y);


    // Enemy Position

    float getEnemyPositionX();
    float getEnemyPositionY();

    // addPlayerBullet

    void setPlayerBullet(float x,float y);


    // Get Enemy bullet

    void setEnemyBullet(float x,float y);


    // Update methods will be adder here

}
