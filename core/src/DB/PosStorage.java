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


    private float enemyBulletX_DB;
    private float enemyBulletY_DB;
    private boolean enemyBulletDirecton_DB;

    private float playerBulletX_DB;
    private float playerBulletY_DB;
    private boolean playerBulletDirecton_DB;

    private boolean newPlayerBullet;
    private boolean newEnemyBullet;

    private boolean endGamePlayer;
    private boolean endGameEnemy;

    private boolean resetEndGame;

    private boolean resetPlayerEndGameOny;


    private static final PosStorage ourInstance = new PosStorage();

    public static  PosStorage getInstance() {
        return ourInstance;
    }

    private PosStorage() {

        endGamePlayer = false;
        endGameEnemy = false;
    }

    public synchronized float getEnemyPosX_DB() {
        return enemyPosX_DB;
    }

    public synchronized void setEnemyPosX_DB(float enemyPosX_DB) {
        this.enemyPosX_DB = enemyPosX_DB;
    }

    public synchronized float getEnemyPosY_DB() {
        return enemyPosY_DB;
    }

    public synchronized void setEnemyPosY_DB(float enemyPosY_DB) {
        this.enemyPosY_DB = enemyPosY_DB;
    }

    public synchronized boolean isUpdate() {
        return isUpdate;
    }

    public synchronized void setUpdate(boolean update) {
        isUpdate = update;
    }

    public synchronized float getPlayerPosX_DB() {
        return playerPosX_DB;
    }

    public synchronized void setPlayerPosX_DB(float playerPosX_DB) {
        this.playerPosX_DB = playerPosX_DB;
    }

    public synchronized float getPlayerPosY_DB() {
        return playerPosY_DB;
    }

    public synchronized void setPlayerPosY_DB(float playerPosY_DB) {
        this.playerPosY_DB = playerPosY_DB;
    }

    public synchronized boolean isNewPlayerBullet() {
        return newPlayerBullet;
    }

    public synchronized void setNewPlayerBullet(boolean newPlayerBullet) {
        this.newPlayerBullet = newPlayerBullet;
    }

    public synchronized boolean isNewEnemyBullet() {
        return newEnemyBullet;
    }

    public synchronized void setNewEnemyBullet(boolean newEnemyBullet) {
        this.newEnemyBullet = newEnemyBullet;
    }

    public synchronized float getEnemyBulletX_DB() {
        return enemyBulletX_DB;
    }

    public synchronized void setEnemyBulletX_DB(float enemyBulletX_DB) {
        this.enemyBulletX_DB = enemyBulletX_DB;
    }

    public float getEnemyBulletY_DB() {
        return enemyBulletY_DB;
    }

    public synchronized void setEnemyBulletY_DB(float enemyBulletY_DB) {
        this.enemyBulletY_DB = enemyBulletY_DB;
    }

    public synchronized float getPlayerBulletX_DB() {
        return playerBulletX_DB;
    }

    public synchronized void setPlayerBulletX_DB(float playerBulletX_DB) {
        this.playerBulletX_DB = playerBulletX_DB;
    }

    public synchronized float getPlayerBulletY_DB() {
        return playerBulletY_DB;
    }

    public synchronized void setPlayerBulletY_DB(float playerBulletY_DB) {
        this.playerBulletY_DB = playerBulletY_DB;
    }

    public  synchronized boolean isEnemyBulletDirecton_DB() {
        return enemyBulletDirecton_DB;
    }

    public  synchronized void setEnemyBulletDirecton_DB(boolean enemyBulletDirecton_DB) {
        this.enemyBulletDirecton_DB = enemyBulletDirecton_DB;
    }

    public  synchronized boolean isPlayerBulletDirecton_DB() {
        return playerBulletDirecton_DB;
    }

    public  synchronized void setPlayerBulletDirecton_DB(boolean playerBulletDirecton_DB) {
        this.playerBulletDirecton_DB = playerBulletDirecton_DB;
    }

    public synchronized boolean isEndGamePlayer() {
        return endGamePlayer;
    }

    public synchronized void setEndGamePlayer(boolean endGamePlayer) {
        this.endGamePlayer = endGamePlayer;
    }

    public synchronized boolean isEndGameEnemy() {
        return endGameEnemy;
    }

    public synchronized void setEndGameEnemy(boolean endGameEnemy) {
        this.endGameEnemy = endGameEnemy;
    }

    public synchronized boolean isResetEndGame() {
        return resetEndGame;
    }

    public synchronized void setResetEndGame(boolean resetEndGame) {
        this.resetEndGame = resetEndGame;
    }



}
