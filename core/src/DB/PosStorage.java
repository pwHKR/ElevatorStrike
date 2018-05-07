package DB;

/**
 * Created by woojen on 2018-05-07.
 */

public class PosStorage {

    // Db

    private float enemyPosX_DB;
    private float  enemyPosY_DB;

    private float playerPosX_DB;
    private float playerPosY_DB;

    private boolean isUpdate;


    private static final PosStorage ourInstance = new PosStorage();

    public static PosStorage getInstance() {
        return ourInstance;
    }

    private PosStorage() {
    }

    public float getEnemyPosX_DB() {
        return enemyPosX_DB;
    }

    public void setEnemyPosX_DB(float enemyPosX_DB) {
        this.enemyPosX_DB = enemyPosX_DB;
    }

    public float getEnemyPosY_DB() {
        return enemyPosY_DB;
    }

    public void setEnemyPosY_DB(float enemyPosY_DB) {
        this.enemyPosY_DB = enemyPosY_DB;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    public float getPlayerPosX_DB() {
        return playerPosX_DB;
    }

    public void setPlayerPosX_DB(float playerPosX_DB) {
        this.playerPosX_DB = playerPosX_DB;
    }

    public float getPlayerPosY_DB() {
        return playerPosY_DB;
    }

    public void setPlayerPosY_DB(float playerPosY_DB) {
        this.playerPosY_DB = playerPosY_DB;
    }
}
