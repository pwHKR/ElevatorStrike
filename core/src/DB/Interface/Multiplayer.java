package DB.Interface;

/**
 * Created by woojen on 2018-04-19.
 *
 * Interface for connecting the game classes with the Database classes
 */

public interface Multiplayer {

    // Player Position

    void setPlayerPosition(float x,float y);


    // Enemy Position

    void getEnemyPosition(float x,float y);

    // addPlayerBullet

    void newPlayerBullet(float x,float y);


    // Get Enemy bullet

    void newEnemyBullet(float x,float y);


    // Update methods will be adder here 

}
