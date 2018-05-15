package com.mygdx.game.Level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Actor.Opponent.Enemy;
import com.mygdx.game.Actor.Player.Player;
import com.mygdx.game.Event.System.Bullet;
import com.mygdx.game.Event.System.Door;
import com.mygdx.game.Event.System.Elevator;
import com.mygdx.game.Level.System.Obstacle;
import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;
import java.util.Iterator;

import DB.PosStorage;


/**
 * Created by woojen on 2018-04-24.
 */

public class LevelFactory implements Screen {

    MyGdxGame game;

    public LevelFactory(MyGdxGame game) {
        this.game = game;
    }


    private float sendLimit;



    private enum GameStatus {
        MENU,
        LEVEL1,
        INSTRUCTIONS,
        GAME_OVER
    }

    private GameStatus gameStatus = GameStatus.MENU;

    //Textures
    private SpriteBatch batch;
    private Texture levelImg;
    //private Texture titleScreen;
    //private Texture instScreen;
    private Texture hpBar;
    private Texture hpBar80;
    private Texture hpBar50;
    private Texture hpBar30;
    private Texture hpBar10;
    private Texture elevatorWire;
    private Texture gameOver;
    private Texture bulletTexture;
    private Texture obstacleTexture;
    private Texture openDoorTexture;
    private Texture closedDoorTexture;
    private Texture osynligTexture;
    private Texture playerIdleTexture;
    private Texture enemyIdleTexture;
    private Texture playerIdleUpdateTexture;

    private Texture playerAnimation1;
    private Texture playerAnimation2;
    private Texture playerAnimation3;
    private Texture playerAnimation4;
    private Texture playerAnimation5;
    private Texture playerAnimation6;
    private Texture playerAnimation7;
    private Texture playerAnimation8;
    private Texture playerAnimation9;

    private Texture playerJumpImage;
    private Texture playerDuckImage;



    OrthographicCamera camera; // resolution fix


    // private Texture Level22;

    //private Texture exitSign;
    //private Texture LevelClear;

    // Game Finished Texture
    //private Texture gameDone;


    //Sounds
    //private Sound gunShotSound;
    private Sound m4Sound;
    //private Sound elevatorSound;
    //private Sound loopSound;
    // private Sound keySound;
    //private Sound guitarLoop;
    //private Sound rocketSound;
    //private Sound doorSound;

    //counters
    //private float counterElevator = 0;


    private float walkCounter = 0;


    //booleans
    private boolean showHP = true; // Temporary boolean for showing hp HUD
    private boolean jumpcheck; // A switch for seeing if there is an active jump or not. Used in the render method later on
    private boolean duckCheck;
    private boolean elevatorUp;


    private boolean walking;

    private boolean walkCounter0;

    private boolean elevatorStopped = true;


    // android logic
    private boolean isShooting = false;
    private boolean androidDown;
    private boolean androidUp;


    //Arraylists level 1
    private ArrayList<Player> players;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> enemyBullets;
    private ArrayList<Door> doors;
    private ArrayList<Elevator> elevators;
    Texture [] animation = new Texture[9];


    //private ArrayList<HealthPack> healthPacks3;
    //private ArrayList<Obstacle> walls;


    // private ArrayList<Explossion> explo;


    // Oponent player

    Enemy opponent;

    // Enemy pos Storage

    private PosStorage gamePosStorage;

    private long prevTime = TimeUtils.millis();


    public void testLevel() {
        checkInput();


        bulletLogic();
        getEnemyBulletLogic();
        checkEnemyCollision();

        playerLifeCheck();
        // checkWallCollision();
        endGame();


        players.get(0).duckSwitchOn();


        // Elevator Floor 1
        if (elevators.get(0).getY() == 30f && TimeUtils.timeSinceMillis(prevTime) < 15000) {


            elevators.get(0).setSpeedY(0);
            if (elevatorStopped == true) {
                //elevatorSound.play();
                elevatorStopped = false;
            }
            // counterElevator = counterElevator + 0.016f;

            if (TimeUtils.timeSinceMillis(prevTime) > 1200) {
                elevators.get(0).startElevatorBottom();
                elevatorUp = true;
            }
        }


        // Elevator Floor 2
        if (elevators.get(0).getY() == 180 && TimeUtils.timeSinceMillis(prevTime) < 15000) {


            elevators.get(0).setSpeedY(0);
            if (elevatorStopped == true) {
                //elevatorSound.play();
                elevatorStopped = false;
            }
            //counterElevator = counterElevator + 0.016f;
        }


        // Elevator Floor 3
        if (elevators.get(0).getY() == 330 && TimeUtils.timeSinceMillis(prevTime) < 15000) {


            elevators.get(0).setSpeedY(0);
            if (elevatorStopped == true) {
                //elevatorSound.play();
                elevatorStopped = false;
            }
            //  counterElevator = counterElevator + 0.016f;


        }
        // Elevator floor 4
        if (elevators.get(0).getY() == 480 && TimeUtils.timeSinceMillis(prevTime) < 15000) {


            elevators.get(0).setSpeedY(0);
            if (elevatorStopped == true) {
                //elevatorSound.play();
                elevatorStopped = false;
            }
            //counterElevator = counterElevator + 0.016f;


            if (TimeUtils.timeSinceMillis(prevTime) > 12000) {
                elevators.get(0).startElevatorTop();
                elevatorUp = false;
            }
        }

        if (TimeUtils.timeSinceMillis(prevTime) >= 12000) {
            if (elevatorUp == false) {
                elevators.get(0).setSpeedY(-2);
            } else if (elevatorUp == true) {
                elevators.get(0).setSpeedY(2);
            }

            // counterElevator = 0;


            elevatorStopped = true;
            prevTime = TimeUtils.millis();
        }


        if (jumpcheck == false) {
            players.get(0).jumpSwitchOff(); // switch (off) for rasing x speed while jumping


            if (duckCheck == false) {
                players.get(0).jumpImage();
            }

        }


        if (jumpcheck == true) {
            players.get(0).jumpImage();

            float yLimit = getJumpLimit(); // The solution with 2 methods for aquriing the yLimit was nessecary-
            //for the algorithm to work. Otherwise the yLimit would change for every new render "loop".


            // Set a number before (f) on the last part to increase or decrease the jump acceleration
            players.get(0).sprite.setPosition(players.get(0).getX(), players.get(0).getY() + 3f); // here<--

            // if player has reached the limit of the jump, set jumpcheck to false ,end the jump and let the gravity kick in
            if (players.get(0).getY() >= yLimit) {
                jumpcheck = false;
            }
        }


        Iterator var1 = players.iterator();

        Player enemy;
        while (var1.hasNext()) {
            enemy = (Player) var1.next();
            enemy.updatePositionFromSpeed();
        }

        var1 = this.bullets.iterator();

        Bullet enemy1;

        while (var1.hasNext()) {
            enemy1 = (Bullet) var1.next();

            if (enemy1 != null) {

                enemy1.updatePositionFromSpeed();
            }
        }



        gamePosStorage = PosStorage.getInstance();
        opponent.setY(gamePosStorage.getEnemyPosY_DB());
        opponent.setX(gamePosStorage.getEnemyPosX_DB());

        gamePosStorage.setPlayerPosX_DB(players.get(0).getX());
        gamePosStorage.setPlayerPosY_DB(players.get(0).getY());





        /*

        for (com.mygdx.game.Actor.Opponent.Enemy e : enemies) {
            e.updatePositionFromSpeed();
            e.setSpeedY(-2);
            checkObstacleCollisionEnemies(e);
        }

*/


        for (Bullet bullet1 : bullets) {
            if (bullet1 != null) {

                bullet1.updatePositionFromSpeed();
                checkBulletCollision(bullet1);
                break;

            }
        }


        // checkEnemyCollision();


        for (Player player : players) {
            player.updatePositionFromSpeed();
            if (jumpcheck == false) {
                // Set gravity for player to -1 when the jump has ended
                player.setSpeedY(-1);
            }
            checkObstacleCollision(player);
        }


        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        camera.update();
        batch.setProjectionMatrix(camera.combined);


        batch.draw(levelImg, 0, 0, 1280, 720);
//

        batch.draw(elevatorWire, elevators.get(0).getX() + 90, elevators.get(0).getY() + 60, 10, 700);

        for (Obstacle obstacle : obstacles) {
            obstacle.draw(batch);
        }

        obstacles.get(16).sprite.setColor(0f);

        for (Elevator elevator : elevators) {
            elevator.updatePositionFromSpeed();
            elevator.draw(batch);
        }





        //ritar ut enemyBullets
        for (Bullet enemyBullet : enemyBullets) {
            if (enemyBullet != null) {

                enemyBullet.updatePositionFromSpeed();
                enemyBullet.draw(batch);
            }
        }



        // Ritar ut dörrar
        for (Door door : doors) {
            door.draw(batch);
        }

          /*

        // android buttons

        batch.draw(upPic,1125,620,50,50);
        batch.draw(downPic,1125,570,50,50);
        batch.draw(xPic,1175,595,50,50);

            */
        // Elevator lines
        obstacles.get(16).sprite.setPosition(elevators.get(0).getX(), elevators.get(0).getY() + 150);


        var1 = this.bullets.iterator();


        while (var1.hasNext()) {
            enemy1 = (Bullet) var1.next();

            if (enemy1 != null) {
                enemy1.draw(this.batch);
            }
        }

        var1 = this.players.iterator();

        while (var1.hasNext()) {
            enemy = (Player) var1.next();


            enemy.updatePositionFromSpeed();
            enemy.draw(batch);
        }
 /*
        var1 = enemies.iterator();

        while (var1.hasNext()) {


            com.mygdx.game.Actor.Opponent.Enemy enemy2 = (com.mygdx.game.Actor.Opponent.Enemy) var1.next();

            enemy2.draw(batch);
        }

        */

        for (Player player : players) {
            checkObstacleCollision(player);

            checkElevatorOverLap(player);
            enterDoor(player);
            player.updatePositionFromSpeed();
            player.draw(batch);


        }

        opponent.draw(batch);


        // Tar bort player bullet om de kom
        for (Bullet bullet : bullets) {
            if (bullet.getX() <= 0 || bullet.getX() >= 1280) {
                bullets.remove(bullet);
                break;
            }
        }

        drawHpBar();
        batch.end();
    }


    private void checkInput() {


        // Android Logic


        androidUp = Gdx.input.justTouched() && Gdx.input.getX(0) >= 1125 && Gdx.input.justTouched()
                && Gdx.input.getY(0) < 620;

        androidDown = Gdx.input.isTouched() && Gdx.input.getX(0) >= 1125 && Gdx.input.isTouched() &&
                Gdx.input.getY() > 620;


        boolean androidLeft = Gdx.input.isTouched() && Gdx.input.getX(0) < 500 &&
                Gdx.input.getX() > 100 &&
                isShooting == false && androidDown == false;

        boolean androidRight = Gdx.input.isTouched() && Gdx.input.getX(0) > 500
                && Gdx.input.getX() <= 1000
                && isShooting == false && androidDown == false;


        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.DOWN)
                || androidLeft && !androidDown) {
            players.get(0).goLeft();
            players.get(0).sprite.setFlip(true, false);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.DOWN)
                || androidRight && !androidDown) {
            players.get(0).goRight();
            players.get(0).sprite.setFlip(false, false);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                || androidLeft || androidRight) {
            walking = true;
            walkCounter = walkCounter + 0.016f;
            walkCounter0 = players.get(0).animation(walkCounter);
            if (walkCounter0 == true) {
                walkCounter = 0f;
            }


        } else {
            walking = false;
        }


        if (walking == false && jumpcheck == false && duckCheck == false && players.get(0).getSpeedY() == 0) {
            players.get(0).idleImage();

        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) || androidUp) {
            // If the player is standing at ground (with yspeed 0) and the other one is to
            //see that the player isnt already in an active jump
            if (players.get(0).getSpeedY() == 0 && jumpcheck == false) {

                //  method call to set the Jump Limit. The reason for having it in checkInput is beacuse we want-
                //the value of the jump Limit to be fixed and not updated every time Renderruns
                setJumpLimit();
                jumpcheck = true;
                players.get(0).jumpSwitchOn(); // Switch (on) for rasing xSpeed while jumping

            }
        }


        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || androidDown) {

            if (players.get(0).getSpeedY() == 0 && jumpcheck == false) {

                duckCheck = true;
                players.get(0).duckImage();
                players.get(0).sprite.setSize(70, 30);


            }


        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) == false || androidDown == false) {

            duckCheck = false;
            players.get(0).sprite.setSize(50, 50);
        }


        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            System.exit(0);
        }


    }


    private void createActors() {
        boolean bulletPos = true;
        players = new ArrayList<Player>();
        enemies = new ArrayList();
        bullets = new ArrayList();
        enemyBullets = new ArrayList();


        Enemy e1 = new Enemy(enemyIdleTexture, 20, 600, 50, 50);
        enemies.add(e1);

        opponent = new Enemy(enemyIdleTexture, 20, 600, 50, 30);


        Player player = new Player(playerIdleTexture, 20, 600, 50, 100, false, false,playerIdleUpdateTexture,
                playerAnimation1,playerAnimation2,playerAnimation3,playerAnimation4,playerAnimation5,playerAnimation6,playerAnimation7,playerAnimation8,
                playerAnimation9,playerJumpImage,playerDuckImage); // new parameters in the Player class

        player.setSpeedY(-1); // gravity to -1
        players.add(player);
    }

    private void createObstacles() {
        obstacles = new ArrayList<Obstacle>();
        doors = new ArrayList<Door>();
        elevators = new ArrayList();
        //walls = new ArrayList();


        //floor 1
        Obstacle ground1 = new Obstacle(obstacleTexture, 0, 10, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground1);
        Obstacle ground2 = new Obstacle(obstacleTexture, 300, 10, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground2);
        Obstacle ground3 = new Obstacle(obstacleTexture, 700, 10, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground3);
        Obstacle ground4 = new Obstacle(obstacleTexture, 1000, 10, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground4);


        //floor 2
        Obstacle ground29 = new Obstacle(obstacleTexture, 0, 150, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground29);
        Obstacle ground30 = new Obstacle(obstacleTexture, 300, 150, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground30);
        Obstacle ground31 = new Obstacle(obstacleTexture, 750, 150, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground31);
        Obstacle ground32 = new Obstacle(obstacleTexture, 1000, 150, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground32);


        //floor 3
        Obstacle ground25 = new Obstacle(obstacleTexture, 0, 300, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground25);
        Obstacle ground26 = new Obstacle(obstacleTexture, 300, 300, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground26);
        Obstacle ground27 = new Obstacle(obstacleTexture, 750, 300, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground27);
        Obstacle ground28 = new Obstacle(obstacleTexture, 1000, 300, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground28);


        //floor 4
        Obstacle ground21 = new Obstacle(obstacleTexture, 0, 450, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground21);
        Obstacle ground22 = new Obstacle(obstacleTexture, 300, 450, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground22);
        Obstacle ground23 = new Obstacle(obstacleTexture, 750, 450, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground23);
        Obstacle ground24 = new Obstacle(obstacleTexture, 1000, 450, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground24);


        Door door1 = new Door(closedDoorTexture, 10, 185, Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 8);
        doors.add(door1);

        Door door2 = new Door(closedDoorTexture, 1100, 335, Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 8);
        doors.add(door2);


        // Exit door

        // Door exitDoor = new Door("doubledoor.png", 1100, 480, 100, 100);
        //exitDoors.add(exitDoor);

        Elevator elevator = new Elevator(openDoorTexture, 560, 400, 120);
        elevator.sprite.setSize(190, 150);
        elevator.setSpeedY(-2);
        elevators.add(elevator);


        // Top line for elevator
        Obstacle line = new Obstacle(osynligTexture, elevators.get(0).getX(), elevators.get(0).getY() + 65, 190, 5);
        obstacles.add(line);



        /*
        // wall right most

        Obstacle wall1 = new Obstacle("wall.png", 1279, 0, 20, 720);
        walls.add(wall1);

        // wall left most

        Obstacle wall2 = new Obstacle("wall.png", -20, 0, 20, 720);
        walls.add(wall2);

        // left Obstacle side walls

        Obstacle sideWall1 = new Obstacle("wall.png", 555, 450, 1, 30);
        walls.add(sideWall1);

        Obstacle sideWall2 = new Obstacle("wall.png", 555, 300, 1, 30);
        walls.add(sideWall2);

        Obstacle sideWall3 = new Obstacle("wall.png", 555, 150, 1, 30);
        walls.add(sideWall3);

        // right Obstacle side walls

        Obstacle sideWall4 = new Obstacle("wall.png", 750, 450, 1, 30);
        walls.add(sideWall4);

        Obstacle sideWall5 = new Obstacle("wall.png", 750, 300, 1, 30);
        walls.add(sideWall5);

        Obstacle sideWall6 = new Obstacle("wall.png", 750, 150, 1, 30);
        walls.add(sideWall6);

        */


    }


    private void bulletLogic() {

        //android logic

        gamePosStorage = PosStorage.getInstance();

        androidDown = Gdx.input.isTouched() && Gdx.input.getX(0) >= 1125 && Gdx.input.isTouched() &&
                Gdx.input.getY() > 620;

        isShooting = Gdx.input.justTouched() && Gdx.input.getX() <= 100 &&
                Gdx.input.isTouched() && Gdx.input.getY() < 620;


        Bullet bullet = new Bullet(bulletTexture, ((Player) this.players.get(0)).getX() + 10.0F, ((Player) this.players.get(0)).getY() + 25.0F, 10);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !Gdx.input.isKeyPressed(Input.Keys.DOWN)
                || isShooting && !androidDown) {
            if (players.get(0).sprite.isFlipX()) {

                bullet.setSpeedX(-6);
                gamePosStorage.setPlayerBulletDirecton_DB(false);

            } else if (players.get(0).sprite.isFlipX() == false) {
                bullet.setSpeedX(6);
                gamePosStorage.setPlayerBulletDirecton_DB(true);
            }
            bullets.add(bullet);

            gamePosStorage.setNewPlayerBullet(true);
            gamePosStorage.setPlayerBulletX_DB(bullet.getX());
            gamePosStorage.setPlayerBulletY_DB(bullet.getY());
            m4Sound.play(0.3f);
        }
    }

    /*
    private void checkWallCollision() {

        if (gameStatus == gameStatus.LEVEL1) {

            for (int i = 0; i < walls.size(); i++) {
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                        && players.get(0).getPlayerRectangle().overlaps(walls.get(i).getCollisionRectangle())) {
                    players.get(0).getSprite().translateX(-3);
                }
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT)
                        && players.get(0).getPlayerRectangle().overlaps(walls.get(i).getCollisionRectangle())) {
                    players.get(0).getSprite().translateX(3);
                }
            }


        }


    } */


    public void enterDoor(Player player) {


        if (gameStatus == GameStatus.LEVEL1) {

            if (player.getPlayerRectangle().overlaps(doors.get(0).getCollisionRectangle())
                    && Gdx.input.isKeyJustPressed(Input.Keys.E) ||
                    player.getPlayerRectangle().overlaps(doors.get(0).getCollisionRectangle())) {
                player.setX(doors.get(1).getX());
                player.setY(doors.get(1).getY());
                // doorSound.play();
            } else if (player.getPlayerRectangle().overlaps(doors.get(1).getCollisionRectangle())
                    && Gdx.input.isKeyJustPressed(Input.Keys.E) ||
                    player.getPlayerRectangle().overlaps(doors.get(1).getCollisionRectangle()) && androidUp) {
                player.setX(doors.get(0).getX());
                player.setY(doors.get(0).getY());
                // doorSound.play();
            }
        }


    }


    public void playerLifeCheck() {

        if (players.get(0).GetHP() <= 0) {
            gameStatus = gameStatus.GAME_OVER;
        }
    }

    public void setJumpLimit() {
        // Algorithm for the JumpLimit. Change the number berofe f to increase or decrease the limit
        sendLimit = players.get(0).getY() + 50f; //<---here
    }

    // Getting the fixed jump limit to the float yLimit variabl
    public float getJumpLimit() {
        return sendLimit;
    }


    private void checkObstacleCollision(Player player) {


        for (Obstacle obstacle : obstacles) {
            if (player.getPlayerRectangle().overlaps(obstacle.getCollisionRectangle()))
                player.setSpeedY(0);
        }


    }


    private void checkElevatorOverLap(Player player) {


        for (Elevator elevator : elevators) {

            if (elevator.getSpeedY() > 0 && player.getPlayerRectangle().overlaps(obstacles.get(16).getCollisionRectangle())) {

                players.get(0).setY(obstacles.get(16).sprite.getY());
            }


            if (player.getPlayerRectangle().overlaps(elevator.getElevatorRectangle())) {

                player.setSpeedY(0);


            }


            if (player.getPlayerRectangle().overlaps(elevator.getElevatorRectangle())) {
                if (elevator.getSpeedY() == 0) {

                    player.sprite.setPosition(player.getX(), elevator.getY() + 10);

                }

                if (elevator.getSpeedY() != 0) {

                    player.sprite.setPosition(elevator.getX() + 60, elevator.getY() + 10);
                }
            }
        }


    }


    public void drawHpBar() {
        if (players.get(0).GetHP() == 100) {
            batch.draw(hpBar, 1023, 682, 257, 36);
        }

        if (players.get(0).GetHP() <= 80) {
            batch.draw(hpBar80, 1023, 682, 257, 36);
        }

        if (players.get(0).GetHP() <= 60) {
            batch.draw(hpBar50, 1023, 682, 257, 36);
        }

        if (players.get(0).GetHP() <= 40) {
            batch.draw(hpBar30, 1023, 682, 257, 36);
        }

        if (players.get(0).GetHP() <= 20) {
            batch.draw(hpBar10, 1023, 682, 257, 36);
        }
    }

    public void renderMenu() {
        Gdx.gl.glClearColor(50, 50, 50, 50);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();


        //batch.draw(titleScreen, 0, 0, 1280, 900);
        if (Gdx.input.justTouched() == true) {
            gameStatus = gameStatus.LEVEL1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            gameStatus = gameStatus.INSTRUCTIONS;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        batch.end();
    }

    public void renderGameOver() {

        Gdx.gl.glClearColor(50, 50, 50, 50);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(gameOver, 0, 0);
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            gameStatus = gameStatus.MENU;
        }
        batch.end();
    }


    private void checkBulletCollision(Bullet bullet1) {
        for (Enemy e : enemies) {
            if (bullet1.getBulletRectangle().overlaps(e.getEnemieRectangle()) || bullet1.getBulletRectangle().
                    overlaps(opponent.getEnemieRectangle())) {
                bullet1.sprite.setPosition(2000, 2000);
                e.SetHP(e.GetHP() - 20);
                //playerScore = playerScore + 10;
                if (e.GetHP() <= 0) {
                    enemies.remove(e);

                    break;

                }

            }

        }
    }

    public void switchMenu() {

        switch (gameStatus) {
            case MENU:
                renderMenu();
                break;
            case LEVEL1:
                testLevel();
                /*highScoreLogic();*/
                break;

            case GAME_OVER:
                renderGameOver();
                break;

        }
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        levelImg = new Texture("brickWall.png");
        //titleScreen = new Texture("gWelcome.png");
        // instScreen = new Texture("gInstructions.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1280,720); // set resolution
        hpBar = new Texture("fullHP.png");
        hpBar80 = new Texture("80hp.png");
        hpBar50 = new Texture("50HP.png");
        hpBar30 = new Texture("30 hp.png");
        hpBar10 = new Texture("10 hp.png");
        elevatorWire = new Texture("wire2.png");
        gameOver = new Texture("Game_over2.png");
        bulletTexture = new Texture("OrangeScale__003.png");
        obstacleTexture = new Texture("we.png");
        openDoorTexture = new Texture("DoorOpen.png");
        closedDoorTexture = new Texture("DoorUnlocked.png");
        osynligTexture = new Texture("osynlig.png");
        enemyIdleTexture = new Texture("s1.png");
        playerIdleTexture = new Texture("Walk_Shoot__005.png");
        playerIdleUpdateTexture = new Texture("i1.png");
        playerAnimation1 = new Texture("Run_Shoot__001.png");
        playerAnimation2 = new Texture("Run_Shoot__002.png");
        playerAnimation3 = new Texture("Run_Shoot__003.png");
        playerAnimation4 = new Texture("Run_Shoot__004.png");
        playerAnimation5 = new Texture("Run_Shoot__005.png");
        playerAnimation6 = new Texture("Run_Shoot__006.png");
        playerAnimation7 = new Texture("Run_Shoot__007.png");
        playerAnimation8 = new Texture("Run_Shoot__008.png");
        playerAnimation9 = new Texture("Run_Shoot__009.png");

        playerJumpImage = new Texture("j1.png");
        playerDuckImage = new Texture("lay.png");

        //Level22 = new Texture("bloody_wall.jpg");

        //exitSign = new Texture("exit_sign.gif");
        //gameDone = new Texture("gameFinished1.png");
        //LevelClear = new Texture("LevelClear.png");
        //scoreFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        //gunShotSound = Gdx.audio.newSound(Gdx.files.internal("Gun_Fire.wav"));
        m4Sound = Gdx.audio.newSound(Gdx.files.internal("M4A1_SingleShot.mp3"));
        //elevatorSound = Gdx.audio.newSound(Gdx.files.internal("Elevator_Stop.wav"));
        //loopSound = Gdx.audio.newSound(Gdx.files.internal("Beyond_Sanity__192.mp3"));
        //  keySound = Gdx.audio.newSound(Gdx.files.internal("gold.wav"));
        // guitarLoop = Gdx.audio.newSound(Gdx.files.internal("eGuitarLoop.wav"));
        //rocketSound = Gdx.audio.newSound(Gdx.files.internal("Rocket-Sound.wav"));
        //doorSound = Gdx.audio.newSound(Gdx.files.internal("doorwood_open.wav"));


        createActors();
        createObstacles();
    }

    @Override
    public void render(float delta) {
        testLevel();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void dispose() {
        levelImg.dispose();
        hpBar80.dispose();
        hpBar10.dispose();
        hpBar30.dispose();
        hpBar50.dispose();
        elevatorWire.dispose();
        batch.dispose();
        m4Sound.dispose();
        hpBar.dispose();
        gameOver.dispose();
        bulletTexture.dispose();
        obstacleTexture.dispose();
        openDoorTexture.dispose();
        closedDoorTexture.dispose();
        osynligTexture.dispose();
        playerIdleTexture.dispose();
        enemyIdleTexture.dispose();
        playerIdleUpdateTexture.dispose();
        playerAnimation1.dispose();
        playerAnimation2.dispose();
        playerAnimation3.dispose();
        playerAnimation4.dispose();
        playerAnimation5.dispose();
        playerAnimation6.dispose();
        playerAnimation7.dispose();
        playerAnimation8.dispose();
        playerAnimation9.dispose();
        playerJumpImage.dispose();
        playerDuckImage.dispose();

    }

    private void checkEnemyCollision() {
        for (Bullet enemyBullet : enemyBullets) {

            if (enemyBullet.getBulletRectangle().overlaps(players.get(0).getPlayerRectangle().fitOutside(players.get(0).getPlayerRectangle()))) {


                players.get(0).SetHP(players.get(0).GetHP() - 20);
                //playerScore = playerScore - 10;
                enemyBullets.remove(enemyBullet);
                break;
            }

            // Remove the bullets that hasnt collided for enemy
            if (enemyBullet.getX() < 0 || enemyBullet.getX() > 1280) {
                enemyBullets.remove(enemyBullet);
                break;
            }


        }



    }

    private void getEnemyBulletLogic(){

        gamePosStorage = PosStorage.getInstance();

        if(gamePosStorage.isNewEnemyBullet()){

        int direction;



        if(gamePosStorage.isEnemyBulletDirecton_DB()){

            direction = 6;
        }

        else {

            direction = -6;
        }


        Bullet bullet = new Bullet(bulletTexture, gamePosStorage.getEnemyBulletX_DB(), gamePosStorage.getEnemyBulletY_DB(), 10);
        bullet.setSpeedX(direction);

        enemyBullets.add(bullet);
        gamePosStorage.setNewEnemyBullet(false);

    }}

    private void endGame(){



        if(players.get(0).GetHP() <= 0){
            gamePosStorage = PosStorage.getInstance();

            gamePosStorage.setEndGamePlayer(true);

            // switch screen here
        }


    }

}







