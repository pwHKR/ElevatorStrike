package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class MyGdxGame extends ApplicationAdapter implements ApplicationListener, InputProcessor {

    private float sendLimit;


    private enum GameStatus {
        MENU,
        LEVEL1,
        LEVEL2,
        LEVEL3,
        LEVELCLEAR,
        LEVELCLEAR2,
        FINISH,
        INSTRUCTIONS,
        GAME_OVER
    }

    private GameStatus gameStatus = GameStatus.MENU;

    //Textures
    private SpriteBatch batch;
    private Texture levelImg;
    private Texture titleScreen;
    private Texture instScreen;
    private Texture hpBar;
    private Texture hpBar80;
    private Texture hpBar50;
    private Texture hpBar30;
    private Texture hpBar10;
    private Texture elevatorWire;
    private Texture gameOver;
    private Texture Level22;
    private Texture exitSign;
    private Texture LevelClear;

    // Game Finished Texture
    private Texture gameDone;


    //space texture
    private Texture space;

    //android button textures

    //private Texture xPic;
    //private Texture upPic;
    //private Texture downPic;

    //Text
    // private BitmapFont scoreFont;
    //private int playerScore = 0;

    //Sounds
    private Sound gunShotSound;
    private Sound m4Sound;
    private Sound elevatorSound;
    //private Sound loopSound;
    private Sound keySound;
    private Sound guitarLoop;
    private Sound rocketSound;
    private Sound doorSound;

    //counters
    private float counterElevator = 0;
    private float counterElevator2 = 0;
    private float counterElevator3 = 0;
    private float counterElevator4 = 0;
    private float counterElevator5 = 0;
    private float idleCounter = 0;
    private float walkCounter = 0;
    private float bossCounterLevel3 = 0;
    private float walkCounterE = 0;
    private float explossionCounter = 0;

    //booleans
    private boolean showHP = true; // Temporary boolean for showing hp HUD
    private boolean jumpcheck; // A switch for seeing if there is an active jump or not. Used in the render method later on
    private boolean duckCheck;
    private boolean elevatorUp;
    private boolean elevatorUp2;
    private boolean elevatorUp3;
    private boolean elevatorUp4;
    private boolean elevatorUp5;
    private boolean once;
    private boolean wrongFloor;
    private boolean explossionQ;

    private boolean onceLevel2;
    private boolean onceLevel2_2;
    private boolean onceLevel2_3;
    private boolean onceLevel2_4;

    private boolean onceLevel3;
    private boolean firstBossLevel3;
    private boolean onceLevel3_2;
    private boolean onceLevel3_3;
    private boolean onceLevel3_4;
    private boolean onceLevel3_5;
    private boolean onceLevel3_6;

    private boolean walking;
    private boolean walkingE;
    private boolean walkCounter0;
    private boolean walkCounterE0;
    private boolean elevatorStopped = true;
    private boolean keySoundPlaying = false;
    private boolean rocketSoundPlaying = false;
    private boolean guitarSoundPlaying = false;

    // android logic
    private boolean  isShooting = false;
    private boolean androidDown;


    //Arraylists level 1
    private ArrayList<Player> players;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<com.mygdx.game.Enemy> enemies;
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> enemyBullets;
    private ArrayList<Door> doors;
    private ArrayList<Elevator> elevators;
    private ArrayList<Door> exitDoors;
    private ArrayList<Key> keys;
    private ArrayList<HealthPack> healthPacks3;
    private ArrayList<Obstacle> walls;

    //Arraylist level 2
    private ArrayList<Obstacle> o2;
    private ArrayList<Obstacle> spike;
    private ArrayList<Elevator> elevators2;
    private ArrayList<Obstacle> walls2;
    private ArrayList<Door> d2;
    private ArrayList<HealthPack> healthPacks2;
    private ArrayList<Key> keys2;

    //Arraylist level 3
    private ArrayList<Obstacle> o3;
    private ArrayList<Obstacle> walls3;
    private ArrayList<Door> doors3;
    private ArrayList<Elevator> elevators3;
    private ArrayList<Obstacle> spikes2;
    private ArrayList<Key> keys3;
    private ArrayList<Explossion> explo;


    @Override
    public void create() {
        batch = new SpriteBatch();
        levelImg = new Texture("brickWall.png");
        titleScreen = new Texture("gWelcome.png");
        instScreen = new Texture("gInstructions.png");
        hpBar = new Texture("fullHP.png");
        hpBar80 = new Texture("80hp.png");
        hpBar50 = new Texture("50HP.png");
        hpBar30 = new Texture("30 hp.png");
        hpBar10 = new Texture("10 hp.png");
        elevatorWire = new Texture("wire2.png");
        gameOver = new Texture("Game_over2.png");
        Level22 = new Texture("bloody_wall.jpg");
        space = new Texture("sb.jpg");
        exitSign = new Texture("exit_sign.gif");
        gameDone = new Texture("gameFinished1.png");
        LevelClear = new Texture("LevelClear.png");
        //scoreFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        gunShotSound = Gdx.audio.newSound(Gdx.files.internal("Gun_Fire.wav"));
        m4Sound = Gdx.audio.newSound(Gdx.files.internal("M4A1_SingleShot.mp3"));
        elevatorSound = Gdx.audio.newSound(Gdx.files.internal("Elevator_Stop.wav"));
        //loopSound = Gdx.audio.newSound(Gdx.files.internal("Beyond_Sanity__192.mp3"));
        keySound = Gdx.audio.newSound(Gdx.files.internal("gold.wav"));
        guitarLoop = Gdx.audio.newSound(Gdx.files.internal("eGuitarLoop.wav"));
        rocketSound = Gdx.audio.newSound(Gdx.files.internal("Rocket-Sound.wav"));
        doorSound = Gdx.audio.newSound(Gdx.files.internal("doorwood_open.wav"));

        /*

        // android bottons

        xPic = new Texture("xPic.png");
        upPic = new Texture("upPic.png");
        downPic = new Texture("downPic.png");

        */

        createActors();
        createObstacles();

        createObstacles2();
        createObstacles3();


        //loopSound.loop();


    }

    private void createActors() {
        boolean bulletPos = true;
        players = new ArrayList<Player>();
        enemies = new ArrayList();
        bullets = new ArrayList();
        enemyBullets = new ArrayList();
        exitDoors = new ArrayList();
        keys = new ArrayList();


        com.mygdx.game.Enemy enemy = new Enemy("s1.png", 900, 500, 50, 30);

        enemies.add(enemy);

        com.mygdx.game.Enemy en2 = new Enemy("s1.png", 50, 100, 50, 30);
        enemies.add(en2);


        com.mygdx.game.Enemy en3 = new Enemy("s1.png", 1000, 100, 50, 30);
        enemies.add(en3);

        com.mygdx.game.Enemy en4 = new Enemy("s1.png", 300, 100, 50, 30);
        enemies.add(en4);

        com.mygdx.game.Enemy en5 = new Enemy("s1.png", 300, 350, 50, 30);
        enemies.add(en5);

        com.mygdx.game.Enemy en6 = new Enemy("s1.png", 1000, 180, 50, 30);
        enemies.add(en6);

        com.mygdx.game.Enemy en7 = new Enemy("s1.png", 300, 180, 50, 30);
        enemies.add(en7);

        com.mygdx.game.Enemy en8 = new Enemy("s1.png", 900, 350, 50, 30);
        enemies.add(en8);


        Player player = new Player("Walk_Shoot__005.png", 20, 600, 50, 100, false, false); // new parameters in the Player class

        player.setSpeedY(-1); // gravity to -1
        players.add(player);
    }

    private void createObstacles() {
        obstacles = new ArrayList<Obstacle>();
        doors = new ArrayList<Door>();
        elevators = new ArrayList();
        walls = new ArrayList();


        //floor 1
        Obstacle ground1 = new Obstacle("we.png", 0, 10, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground1);
        Obstacle ground2 = new Obstacle("we.png", 300, 10, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground2);
        Obstacle ground3 = new Obstacle("we.png", 700, 10, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground3);
        Obstacle ground4 = new Obstacle("we.png", 1000, 10, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground4);


        //floor 2
        Obstacle ground29 = new Obstacle("we.png", 0, 150, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground29);
        Obstacle ground30 = new Obstacle("we.png", 300, 150, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground30);
        Obstacle ground31 = new Obstacle("we.png", 750, 150, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground31);
        Obstacle ground32 = new Obstacle("we.png", 1000, 150, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground32);


        //floor 3
        Obstacle ground25 = new Obstacle("we.png", 0, 300, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground25);
        Obstacle ground26 = new Obstacle("we.png", 300, 300, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground26);
        Obstacle ground27 = new Obstacle("we.png", 750, 300, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground27);
        Obstacle ground28 = new Obstacle("we.png", 1000, 300, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground28);


        //floor 4
        Obstacle ground21 = new Obstacle("we.png", 0, 450, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground21);
        Obstacle ground22 = new Obstacle("we.png", 300, 450, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground22);
        Obstacle ground23 = new Obstacle("we.png", 750, 450, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground23);
        Obstacle ground24 = new Obstacle("we.png", 1000, 450, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        obstacles.add(ground24);


        Door door1 = new Door("DoorUnlocked.png", 10, 185, Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 8);
        doors.add(door1);

        Door door2 = new Door("DoorUnlocked.png", 1100, 335, Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 8);
        doors.add(door2);


        // Exit door

        Door exitDoor = new Door("doubledoor.png", 1100, 480, 100, 100);
        exitDoors.add(exitDoor);

        Elevator elevator = new Elevator("DoorOpen.png", 560, 400, 120);
        elevator.sprite.setSize(190, 150);
        elevator.setSpeedY(-2);
        elevators.add(elevator);

        // key for exit door

        Key keyLevel1 = new Key("key1.png", 800, 40, 60, 40);
        keys.add(keyLevel1);

        // Top line for elevator
        Obstacle line = new Obstacle("osynlig.png", elevators.get(0).getX(), elevators.get(0).getY() + 65, 190, 5);
        obstacles.add(line);


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


    }

    public void createActors2() {


        com.mygdx.game.Enemy e1 = new Enemy("s1.png", 500, 100, 50, 50);
        enemies.add(e1);

        com.mygdx.game.Enemy e2 = new Enemy("s1.png", 300, 100, 50, 30);
        enemies.add(e2);


    }

    public void createObstacles2() {

        o2 = new ArrayList();
        spike = new ArrayList();
        elevators2 = new ArrayList();
        walls2 = new ArrayList();
        d2 = new ArrayList();
        healthPacks2 = new ArrayList();
        keys2 = new ArrayList();


        //floor 1
        Obstacle g1 = new Obstacle("we.png", 0, 15, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        o2.add(g1);
        Obstacle g2 = new Obstacle("we.png", 300, 15, Gdx.graphics.getWidth() / 4 + 5, Gdx.graphics.getHeight() / 20);
        o2.add(g2);
        Obstacle g3 = new Obstacle("we.png", 750, 15, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        o2.add(g3);
        Obstacle g4 = new Obstacle("we.png", 1000, 15, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        o2.add(g4);
        //floor 2
        Obstacle g5 = new Obstacle("we.png", 0, 155, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        o2.add(g5);
        Obstacle g6 = new Obstacle("we.png", 300, 155, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        o2.add(g6);
        Obstacle g7 = new Obstacle("we.png", 500, 155, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        o2.add(g7);
        Obstacle g8 = new Obstacle("we.png", 750, 155, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        o2.add(g8);
        //floor 3
        Obstacle g9 = new Obstacle("we.png", 200, 305, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        o2.add(g9);
        Obstacle g10 = new Obstacle("we.png", 500, 305, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        o2.add(g10);
        Obstacle g11 = new Obstacle("we.png", 750, 305, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        o2.add(g11);
        Obstacle g12 = new Obstacle("we.png", 1000, 305, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        o2.add(g12);
        //floor 4
        Obstacle g13 = new Obstacle("we.png", 0, 455, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        o2.add(g13);
        Obstacle g14 = new Obstacle("we.png", 300, 455, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        o2.add(g14);
        Obstacle g15 = new Obstacle("we.png", 500, 455, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        o2.add(g15);
        Obstacle g16 = new Obstacle("we.png", 750, 455, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        o2.add(g16);


        Obstacle spike1 = new Obstacle("spike.png", 610, -30, 80, 50);
        spike1.sprite.setSize(150, 50);
        spike.add(spike1);

        // Low right elevator
        Elevator elevator1 = new Elevator("DoorOpen.png", 1075, 50, 120);

        elevators2.add(elevator1);
        elevator1.sprite.setSize(190, 130);

        // 2nd floor left elevator
        Elevator elevator2 = new Elevator("DoorOpen.png", 7, 185, 120);

        elevators2.add(1, elevator2);
        elevator2.sprite.setSize(190, 130);

        // 3th floor
        Elevator elevator3 = new Elevator("DoorOpen.png", 1075, 335, 120);
        elevators2.add(2, elevator3);
        elevator3.sprite.setSize(190, 130);


        // visible wall
        Obstacle wallL2_1 = new Obstacle("wall.png", 500, 180, 20, 100);
        walls2.add(wallL2_1);


        // wall right most

        Obstacle wall2_2 = new Obstacle("wall.png", 1279, 0, 20, 720);
        walls2.add(wall2_2);

        // wall left most

        Obstacle wall2_3 = new Obstacle("wall.png", -20, 0, 20, 720);
        walls2.add(wall2_3);

        // Obstacle side walls

        Obstacle sideWall1_2 = new Obstacle("wall.png", 625, 5, 1, 30);
        walls2.add(sideWall1_2);

        Obstacle sideWall2_2 = new Obstacle("wall.png", 750, 5, 1, 30);
        walls2.add(sideWall2_2);

        Obstacle sideWall3_2 = new Obstacle("wall.png", 1070, 150, 1, 30);
        walls2.add(sideWall3_2);

        Obstacle sideWall4_2 = new Obstacle("wall.png", 200, 300, 1, 30);
        walls2.add(sideWall4_2);

        Obstacle sideWall5_2 = new Obstacle("wall.png", 1070, 450, 1, 30);
        walls2.add(sideWall5_2);


        //doors
        Door door1_l2 = new Door("DoorUnlocked.png", 800, 180, Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 8);
        d2.add(door1_l2);

        Door door2_l2 = new Door("DoorUnlocked.png", 400, 180, Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 8);
        d2.add(door2_l2);

        Door door3_l2 = new Door("DoorUnlocked.png", 800, 330, Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 8);
        d2.add(door3_l2);

        Door door4_l2 = new Door("DoorUnlocked.png", 10, 485, Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 8);
        d2.add(door4_l2);

        // Key level 2

        Key keyLevel2 = new Key("key1.png", 900, 490, 60, 40);
        keys2.add(keyLevel2);

        //Exit Door

        Door exitDoorL2 = new Door("doubledoor.png", 10, 40, 100, 100);
        exitDoors.add(1, exitDoorL2);

        HealthPack healthPack1Level2 = new HealthPack("HealthPack.png", 150, 50, 50, 50);
        healthPacks2.add(healthPack1Level2);

        HealthPack healthPack2Level2 = new HealthPack("HealthPack.png", 240, 220, 50, 50);
        healthPacks2.add(healthPack2Level2);


    }

    public void createActors3() {


    }

    public void createObstacles3() {
        healthPacks3 = new ArrayList();
        o3 = new ArrayList<Obstacle>();
        walls3 = new ArrayList<Obstacle>();
        doors3 = new ArrayList<Door>();
        elevators3 = new ArrayList<Elevator>();
        spikes2 = new ArrayList<Obstacle>();
        keys3 = new ArrayList<Key>();
        explo = new ArrayList<Explossion>();


        //floor 1
        Obstacle d1 = new Obstacle("we.png", 0, 15, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        o3.add(d1);
        Obstacle d2 = new Obstacle("we.png", 300, 15, Gdx.graphics.getWidth() / 4 + 5, Gdx.graphics.getHeight() / 20);
        o3.add(d2);

        Obstacle d3 = new Obstacle("we.png", 450, 15, Gdx.graphics.getWidth() / 4 + 5, Gdx.graphics.getHeight() / 20);
        o3.add(d3);
        Obstacle d4 = new Obstacle("we.png", 750, 15, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        o3.add(d4);


        //floor 2
        Obstacle d6 = new Obstacle("we.png", 300, 155, Gdx.graphics.getWidth() / 4 + 5, Gdx.graphics.getHeight() / 20);
        o3.add(d6);
        Obstacle d7 = new Obstacle("we.png", 450, 155, Gdx.graphics.getWidth() / 4 + 5, Gdx.graphics.getHeight() / 20);
        o3.add(d7);
        Obstacle d8 = new Obstacle("we.png", 750, 155, Gdx.graphics.getWidth() / 5 + 15, Gdx.graphics.getHeight() / 20);
        o3.add(d8);


        //floor 3
        Obstacle d9 = new Obstacle("we.png", 0, 305, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        o3.add(d9);
        Obstacle d10 = new Obstacle("we.png", 300, 305, Gdx.graphics.getWidth() / 4 + 5, Gdx.graphics.getHeight() / 20);
        o3.add(d10);

        Obstacle d11 = new Obstacle("we.png", 450, 305, Gdx.graphics.getWidth() / 4 + 5, Gdx.graphics.getHeight() / 20);
        o3.add(d11);
        Obstacle d12 = new Obstacle("we.png", 600, 305, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20);
        o3.add(d12);

        //floor 4
        Obstacle d13 = new Obstacle("we.png", 0, 455, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 20);
        o3.add(d13);
        Obstacle d14 = new Obstacle("we.png", 300, 455, Gdx.graphics.getWidth() / 4 + 5, Gdx.graphics.getHeight() / 20);
        o3.add(d14);

        Obstacle d15 = new Obstacle("we.png", 450, 455, Gdx.graphics.getWidth() / 4 + 5, Gdx.graphics.getHeight() / 20);
        o3.add(d15);
        Obstacle d16 = new Obstacle("we.png", 750, 455, Gdx.graphics.getWidth() / 5 + 15, Gdx.graphics.getHeight() / 20);
        o3.add(d16);


        //Wall right
        Obstacle wall3_1 = new Obstacle("wall.png", 1000, 150, 20, 330);
        walls3.add(wall3_1);

        // Wall right most
        Obstacle wall3_2 = new Obstacle("wall.png", 1278, 0, 20, 720);
        walls3.add(wall3_2);

        //Wall left most

        Obstacle wall3_3 = new Obstacle("wall.png", -3, 0, 20, 720);
        walls3.add(wall3_3);

        // Obstacle side walls

        Obstacle sideWall1_3 = new Obstacle("wall.png", 1007, 10, 1, 30);
        walls3.add(sideWall1_3);

        Obstacle sideWall2_3 = new Obstacle("wall.png", 300, 150, 1, 30);
        walls3.add(sideWall2_3);

        Obstacle sideWall3_3 = new Obstacle("wall.png", 860, 300, 1, 30);
        walls3.add(sideWall3_3);

        Obstacle sideWall4_3 = new Obstacle("wall.png", 1025, 450, 1, 30);
        walls3.add(sideWall4_3);


        //doors
        Door door1_l3 = new Door("DoorUnlocked.png", 30, 40, Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 8);
        doors3.add(door1_l3);

        Door door2_l3 = new Door("DoorUnlocked.png", 300, 180, Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 8);
        doors3.add(door2_l3);

        // Elevator right most

        Elevator elevator4 = new Elevator("DoorOpen.png", 1025, 480, 120);

        elevators3.add(elevator4);
        elevator4.sprite.setSize(110, 100);

        // Elevator left most

        Elevator elevator5 = new Elevator("DoorOpen.png", 870, 190, 120);

        elevators3.add(elevator5);
        elevator5.sprite.setSize(110, 100);

        // Spikes

        Obstacle spike2 = new Obstacle("spike.png", 1000, -30, 80, 50);
        spike2.sprite.setSize(300, 50);
        spikes2.add(spike2);

        //key
        Key keyLevel3 = new Key("cohete_off.png", 40, 340, 60, 40);
        keys3.add(keyLevel3);

        //healthpacks
        HealthPack healthPack1Level3 = new HealthPack("HealthPack.png", 600, 200, 50, 50);
        healthPacks3.add(healthPack1Level3);

        // elevator top line

        // Top line for elevator
        Obstacle line3 = new Obstacle("osynlig.png", elevators3.get(0).getX(), elevators3.get(0).getY() + 85, 110, 5);
        o3.add(line3);


        Explossion exp = new Explossion("MidAirExplo__001.png", elevators3.get(0).getX(), elevators3.get(0).getY(), 70);
        explo.add(exp);

        Explossion exp1 = new Explossion("MidAirExplo__001.png", elevators3.get(0).getX(), elevators3.get(0).getY(), 70);
        explo.add(exp1);

    }

    /*public void highScoreLogic() {
        scoreFont.setColor(1, 1, 1, 1);
        scoreFont.getData().setScale(0.4f);
        GlyphLayout layout2 = new GlyphLayout(scoreFont, "Score: " + playerScore);

        if (gameStatus == GameStatus.LEVEL1 || gameStatus == GameStatus.LEVEL2) {
            batch.begin();
            scoreFont.draw(batch, layout2, 10, 700);
            scoreFont.draw(batch, "Your objective is to get the key and open the door", 200, 700);
            batch.end();


        }
        if (gameStatus == GameStatus.LEVEL3) {
            batch.begin();
            scoreFont.draw(batch, layout2, 10, 700);
            scoreFont.draw(batch, "Your objective is to get the rocket and exit", 200, 700);
            batch.end();
        }

        if (gameStatus == GameStatus.FINISH) {
            batch.begin();
            scoreFont.draw(batch, "Your final score: " + playerScore, 400, 300);
            batch.end();
        }
    }
 */

    public void checkInput() {


        // Android Logic



        boolean androidUp = Gdx.input.justTouched() && Gdx.input.getX(0) >= 1125 && Gdx.input.justTouched()
                && Gdx.input.getY(0) < 620 ;

        androidDown = Gdx.input.isTouched() && Gdx.input.getX(0) >= 1125 && Gdx.input.isTouched() &&
                Gdx.input.getY() >620;



        boolean androidLeft = Gdx.input.isTouched() && Gdx.input.getX(0) < 500 &&
                Gdx.input.getX() >100 &&
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


        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)  || androidDown) {

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
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            gameStatus = gameStatus.LEVEL2;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
            gameStatus = gameStatus.LEVEL3;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            System.exit(0);
        }


    }


    public void checkPlayerXMoveTowards() {
        for (com.mygdx.game.Enemy enemy : enemies) {

            float distanceLeft = players.get(0).getX() - enemy.getX();
            float distanceUp = players.get(0).getY() - enemy.getY();
            float distanceRight = enemy.getX() - players.get(0).getX();
            float distanceDown = players.get(0).getY() - enemy.getY();

            Bullet enemyBullet = new Bullet("OrangeScale__003.png", enemy.getX() + 10.0F, enemy.getY() + 40.0F, 10);

            Random shoot = new Random();
            int shot;


            if (distanceLeft >= 200.0F && distanceUp <= 25.0F && distanceDown <= 25.0F) {
                enemy.sprite.setFlip(false, false);
                enemy.sprite.translateX(2.0F);

                walkingE = true;
                walkCounterE = walkCounterE + 0.016f;
                walkCounterE0 = enemy.animation(walkCounterE);
                if (walkCounterE0 == true) {
                    walkCounterE = 0f;

                } else {
                    walkingE = false;
                }


            }

            if (distanceRight >= 200.0F && distanceUp <= 25.0F && distanceDown <= 25.0F) {
                enemy.sprite.setFlip(true, false);
                enemy.sprite.translateX(-2.0F);


                walkingE = true;
                walkCounterE = walkCounterE + 0.016f;
                walkCounterE0 = enemy.animation(walkCounterE);
                if (walkCounterE0 == true) {
                    walkCounterE = 0f;
                } else {
                    walkingE = false;
                }
            }


            //idle läge när spelaren inte är i "attackState"
            /*if ((distanceLeft >= 400.0F == false && distanceUp <= 50.0F == false &&
                    distanceUp <= 50.0F == false && distanceUp <= 50.0F == false && distanceRight >= 400.0F == false)) {

                idleCounter = idleCounter + 0.016f;
                if (idleCounter < 8f) {
                    enemy.sprite.setFlip(false, false);
                    enemy.sprite.translateX(1f);


                    walkingE = true;
                    walkCounterE = walkCounterE + 0.016f;
                    walkCounterE0 = enemy.animation(walkCounterE);
                    if (walkCounterE0 == true) {
                        walkCounterE = 0f;
                    } else {
                        walkingE = false;

                    }


                }
                if (idleCounter > 8f) {
                    enemy.sprite.setFlip(true, false);
                    enemy.sprite.translateX(-1f);


                    walkingE = true;
                    walkCounterE = walkCounterE + 0.016f;
                    walkCounterE0 = enemy.animation(walkCounterE);
                    if (walkCounterE0 == true) {
                        walkCounterE = 0f;
                    } else {
                        walkingE = false;
                    }


                }


            }*/

            if (idleCounter > 16f) {

                idleCounter = 0;
            }


            //kollar bara om player är i range
            if (distanceUp <= 25.0F && distanceLeft < 0.0F && distanceDown <= 25.0F) {


                //Randomar när fienden skjuter
                for (int j = 1; j < 10; j++) {
                    shot = shoot.nextInt(800);
                    if (shot == 1) {

                        enemyBullet.setSpeedX(-6);
                        enemyBullets.add(enemyBullet);
                        gunShotSound.play(0.3f);
                        enemy.idleImage();
                    }
                }

            } else if (distanceUp <= 50.0F && distanceLeft > 0.0F && distanceDown <= 25.0F) {


                //Randomar när fienden skjuter
                for (int j = 1; j < 10; j++) {
                    shot = shoot.nextInt(400);
                    if (shot == 1) {

                        gunShotSound.play(0.3f);
                        enemyBullet.setSpeedX(6);
                        enemyBullets.add(enemyBullet);
                        enemy.idleImage();


                    }
                }
            }

        }
        // Tar bort enemybullets om de kommer utanför skärmen
        for (Bullet Enemybullet : enemyBullets) {
            if (Enemybullet.getX() <= 0 || Enemybullet.getX() >= 1280) {
                enemyBullets.remove(Enemybullet);
                break;
            }
        }
    }


    public void bulletLogic() {

        //android logic

        androidDown = Gdx.input.isTouched() && Gdx.input.getX(0) >= 1125 && Gdx.input.isTouched() &&
                Gdx.input.getY() >620;

        isShooting = Gdx.input.justTouched() && Gdx.input.getX() <= 100 &&
                Gdx.input.isTouched() && Gdx.input.getY() < 620;


        Bullet bullet = new Bullet("OrangeScale__003.png", ((Player) this.players.get(0)).getX() + 10.0F, ((Player) this.players.get(0)).getY() + 25.0F, 10);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !Gdx.input.isKeyPressed(Input.Keys.DOWN)
                || isShooting && !androidDown) {
            if (players.get(0).sprite.isFlipX()) {

                bullet.setSpeedX(-6);
            } else if (players.get(0).sprite.isFlipX() == false) {
                bullet.setSpeedX(6);
            }
            bullets.add(bullet);
            m4Sound.play(0.3f);
        }
    }

    public void render() {

        switch (gameStatus) {
            case MENU:
                renderMenu();
                break;
            case INSTRUCTIONS:
                renderInstructions();
                break;
            case LEVEL1:
                renderRunning();
                /*highScoreLogic();*/
                break;
            case LEVEL2:
                renderLevel2();
                //highScoreLogic();
                break;
            case LEVEL3:
                renderLevel3();
                //highScoreLogic();
                break;
            case GAME_OVER:
                renderGameOver();
                break;
            case LEVELCLEAR:
                renderLevelClear();
                break;
            case LEVELCLEAR2:
                renderLevelClear2();
                break;
            case FINISH:
                renderFinish();
                //highScoreLogic();
                break;
        }
    }


    public void renderRunning() {
        checkInput();
        checkPlayerXMoveTowards();
        bulletLogic();
        exitDoorCheck();
        keyCheck();
        playerLifeCheck();
        checkWallCollision();
        checkWallCollisionEnemy();


        if (keys.get(0).getHasKey() == true && once == false) {

            com.mygdx.game.Enemy santa = new Enemy("s1.png", 1100, 480, 100, 400);

            santa.sprite.flip(true, false);
            enemies.add(santa);
            once = true;
        }


        players.get(0).duckSwitchOn();

        // Elevator Floor 1
        if (elevators.get(0).getY() == 30f && counterElevator < 4.8f) {

            elevators.get(0).setSpeedY(0);
            if (elevatorStopped == true) {
                elevatorSound.play();
                elevatorStopped = false;
            }
            counterElevator = counterElevator + 0.016f;

            if (counterElevator > 4.8f) {
                elevators.get(0).startElevatorBottom();
                elevatorUp = true;
            }
        }


        // Elevator Floor 2
        if (elevators.get(0).getY() == 180 && counterElevator < 4.8f) {

            elevators.get(0).setSpeedY(0);
            if (elevatorStopped == true) {
                elevatorSound.play();
                elevatorStopped = false;
            }
            counterElevator = counterElevator + 0.016f;
        }


        // Elevator Floor 3
        if (elevators.get(0).getY() == 330 && counterElevator < 4.8f) {

            elevators.get(0).setSpeedY(0);
            if (elevatorStopped == true) {
                elevatorSound.play();
                elevatorStopped = false;
            }
            counterElevator = counterElevator + 0.016f;


        }
        // Elevator floor 4
        if (elevators.get(0).getY() == 480 && counterElevator < 4.8f) {

            elevators.get(0).setSpeedY(0);
            if (elevatorStopped == true) {
                elevatorSound.play();
                elevatorStopped = false;
            }
            counterElevator = counterElevator + 0.016f;

            if (counterElevator > 4.8f) {
                elevators.get(0).startElevatorTop();
                elevatorUp = false;
            }
        }

        if (counterElevator >= 4.8f) {
            if (elevatorUp == false) {
                elevators.get(0).setSpeedY(-2);
            } else if (elevatorUp == true) {
                elevators.get(0).setSpeedY(2);
            }

            counterElevator = 0;
            elevatorStopped = true;
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

        for (com.mygdx.game.Enemy e : enemies) {
            e.updatePositionFromSpeed();
            e.setSpeedY(-2);
            checkObstacleCollisionEnemies(e);
        }


        for (Bullet bullet1 : bullets) {
            if (bullet1 != null) {
                for (com.mygdx.game.Enemy e : enemies) {
                    bullet1.updatePositionFromSpeed();
                    checkBulletCollision(bullet1);
                    break;
                }
            }
        }

        checkEnemyCollision();


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


        batch.draw(levelImg, 0, 0, 1280, 720);
        batch.draw(exitSign, 1125, 620, 60, 30);

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


        // Ritar ut exit door

        exitDoors.get(0).draw(batch);

        // Ritar ut dörrar
        for (Door door : doors) {
            door.draw(batch);
        }

        //Ritar ut exitKey

        if (keys.get(0).getHasKey() == false) {
            keys.get(0).draw(batch);
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

        var1 = enemies.iterator();

        while (var1.hasNext()) {


            com.mygdx.game.Enemy enemy2 = (com.mygdx.game.Enemy) var1.next();

            enemy2.draw(batch);
        }

        for (Player player : players) {
            checkObstacleCollision(player);

            checkElevatorOverLap(player);
            enterDoor(player);
            player.updatePositionFromSpeed();
            player.draw(batch);
        }


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


        batch.draw(titleScreen, 0, 0, 1280, 900);
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

    public void renderLevel2() {


        if (onceLevel2 == false) {
            bullets.clear();
            enemies.clear();


            createActors2();
            onceLevel2 = true;
            players.get(0).sprite.setPosition(100, 100);
        }

        checkInput();
        checkPlayerXMoveTowards();
        healthPackCollision();
        keyCheck();
        bulletLogic();
        exitDoorCheck();
        checkWallCollisionEnemy();

        playerLifeCheck();

        if (keys2.get(0).getHasKey() == true && onceLevel2_4 == false) {

            com.mygdx.game.Enemy boss2 = new Enemy("s1.png", 900, 50, 50, 600);

            enemies.add(boss2);

            onceLevel2_2 = false;
            onceLevel2_3 = false; // respawn enemies
            onceLevel2_2 = false;

            onceLevel2_4 = true;
        }

        players.get(0).duckSwitchOn();

        if (jumpcheck == false) {
            players.get(0).jumpSwitchOff(); // switch (off) for rasing x speed while jumping


            if (duckCheck == false) {
                players.get(0).jumpImage();
            }

        }


        if (jumpcheck == true) {
            // Need to fix sizes on jump images
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

        // enemy spawn
        if (players.get(0).getY() > 180 && onceLevel2_2 == false) {
            com.mygdx.game.Enemy e1L3 = new Enemy("s1.png", 900, 255, 50, 50);
            enemies.add(e1L3);

            com.mygdx.game.Enemy e1L4 = new Enemy("s1.png", 800, 255, 50, 50);
            enemies.add(e1L4);


            com.mygdx.game.Enemy e1L6 = new Enemy("s1.png", 200, 255, 50, 50);
            enemies.add(e1L6);


            onceLevel2_2 = true;
        }

        if (players.get(0).getY() > 280 && onceLevel2_3 == false) {
            com.mygdx.game.Enemy f2 = new Enemy("s1.png", 400, 485, 50, 100);
            enemies.add(f2);

            com.mygdx.game.Enemy f21 = new Enemy("s1.png", 700, 485, 50, 100);
            enemies.add(f21);


            onceLevel2_3 = true;


        }

        for (Player player : players) {
            player.updatePositionFromSpeed();
            if (jumpcheck == false) {
                // Set gravity for player to -1 when the jump has ended
                player.setSpeedY(-1);
            }
            checkObstacleCollision(player);
        }

        for (com.mygdx.game.Enemy e : enemies) {
            e.updatePositionFromSpeed();
            e.setSpeedY(-2);
            checkObstacleCollisionEnemies(e);
        }


        // Low right elevator

        // floor 1
        if (elevators2.get(0).getY() == 50f && counterElevator < 4.8f) {

            elevators2.get(0).setSpeedY(0);
            if (elevatorStopped == true) {
                elevatorSound.play();
                elevatorStopped = false;
            }
            counterElevator = counterElevator + 0.016f;

            if (counterElevator > 4.8f) {
                elevators2.get(0).startElevatorBottom();
                elevatorUp = true;
            }
        }

        // floor 2
        if (elevators2.get(0).getY() == 180 && counterElevator < 4.8f) {

            elevators2.get(0).setSpeedY(0);
            if (elevatorStopped == true) {
                elevatorSound.play();
                elevatorStopped = false;
            }
            counterElevator = counterElevator + 0.016f;

            if (counterElevator > 4.8f) {
                elevators2.get(0

                ).startElevatorTop();
                elevatorUp = false;
            }
        }

        if (counterElevator >= 4.8f) {
            if (elevatorUp == false) {
                elevators2.get(0).setSpeedY(-2);
            } else if (elevatorUp == true) {
                elevators2.get(0).setSpeedY(2);
            }

            counterElevator = 0;
            elevatorStopped = true;
        }


        //2nd floor left elevator


        // floor 2 left
        if (elevators2.get(1).getY() == 185f && counterElevator2 < 4.8f) {

            elevators2.get(1).setSpeedY(0);
            if (elevatorStopped == true) {
                elevatorSound.play();
                elevatorStopped = false;
            }
            counterElevator2 = counterElevator2 + 0.016f;


            if (counterElevator2 > 4.8f)
                elevators2.get(1).startElevatorBottom();
            elevatorUp2 = true;
        }

        // Tar bort player bullet
        for (Bullet bullet : bullets) {
            if (bullet.getX() <= 0 || bullet.getX() >= 1280) {
                bullets.remove(bullet);
                break;
            }
        }

        // floor 3 left

        if (elevators2.get(1).getY() >= 330 && counterElevator2 < 4.8f) {

            elevators2.get(1).setSpeedY(0);
            if (elevatorStopped == true) {
                elevatorSound.play();
                elevatorStopped = false;
            }
            counterElevator2 = counterElevator2 + 0.016f;

            if (counterElevator2 > 4.8f)
                elevators2.get(1).startElevatorTop();
            elevatorUp2 = false;
        }


        if (counterElevator2 >= 4.8f) {
            if (elevatorUp2 == false) {
                elevators2.get(1).setSpeedY(-2);
            } else if (elevatorUp2 == true) {
                elevators2.get(1).setSpeedY(2);
            }

            counterElevator2 = 0;
            elevatorStopped = true;
        }


        // 3th floor right elevator


        // floor 3

        if (elevators2.get(2).getY() == 335f && counterElevator3 < 4.8f) {

            elevators2.get(2).setSpeedY(0);
            if (elevatorStopped == true) {
                elevatorSound.play();
                elevatorStopped = false;
            }
            counterElevator3 = counterElevator3 + 0.016f;


            if (counterElevator3 > 4.8f)
                elevators2.get(2).startElevatorBottom();
            elevatorUp3 = true;
        }


        // floor 4

        if (elevators2.get(2).getY() >= 470 && counterElevator3 < 4.8f) {

            elevators2.get(2).setSpeedY(0);
            if (elevatorStopped == true) {
                elevatorSound.play();
                elevatorStopped = false;
            }
            counterElevator3 = counterElevator3 + 0.016f;

            if (counterElevator3 > 4.8f)
                elevators2.get(2).startElevatorTop();
            elevatorUp3 = false;
        }


        if (counterElevator3 >= 4.8f) {
            if (elevatorUp3 == false) {
                elevators2.get(2).setSpeedY(-2);
            } else if (elevatorUp3 == true) {
                elevators2.get(2).setSpeedY(2);
            }

            counterElevator3 = 0;
            elevatorStopped = true;
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

        for (com.mygdx.game.Enemy e : enemies) {
            e.updatePositionFromSpeed();
            e.setSpeedY(-2);
            checkObstacleCollisionEnemies(e);
        }


        for (Bullet bullet1 : bullets) {
            if (bullet1 != null) {
                for (com.mygdx.game.Enemy e : enemies) {
                    bullet1.updatePositionFromSpeed();
                    checkBulletCollision(bullet1);
                    break;
                }
            }
        }

        checkEnemyCollision();


        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();


        batch.draw(Level22, 0, 0, 1280, 720);
        batch.draw(exitSign, 115, 80, 60, 30);


        if (elevators2.get(0).getY() >= 50 && elevators2.get(0).getY() <= 80) {


            batch.draw(elevatorWire, elevators2.get(0).getX() + 90, elevators2.get(0).getY() + 60, 10, 200);
        }

        if (elevators2.get(0).getY() >= 81 && elevators2.get(0).getY() <= 101) {

            batch.draw(elevatorWire, elevators2.get(0).getX() + 90, elevators2.get(0).getY() + 60, 10, 160);

        }

        if (elevators2.get(0).getY() >= 102 && elevators2.get(0).getY() <= 131) {

            batch.draw(elevatorWire, elevators2.get(0).getX() + 90, elevators2.get(0).getY() + 60, 10, 140);

        }

        if (elevators2.get(0).getY() >= 132 && elevators2.get(0).getY() <= 160) {

            batch.draw(elevatorWire, elevators2.get(0).getX() + 90, elevators2.get(0).getY() + 60, 10, 110);

        }

        if (elevators2.get(0).getY() >= 161 && elevators2.get(0).getY() <= 180) {

            batch.draw(elevatorWire, elevators2.get(0).getX() + 90, elevators2.get(0).getY() + 60, 10, 80);

        }


        // elevators2.get(1) ElevatorWire logic

        if (elevators2.get(1).getY() >= 185 && elevators2.get(1).getY() <= 210) {


            batch.draw(elevatorWire, elevators2.get(1).getX() + 90, elevators2.get(1).getY() + 80, 10, 190);
        }


        if (elevators2.get(1).getY() >= 211 && elevators2.get(1).getY() <= 231) {

            batch.draw(elevatorWire, elevators2.get(1).getX() + 90, elevators2.get(1).getY() + 80, 10, 160);

        }

        if (elevators2.get(1).getY() >= 232 && elevators2.get(1).getY() <= 262) {

            batch.draw(elevatorWire, elevators2.get(1).getX() + 90, elevators2.get(1).getY() + 80, 10, 140);

        }

        if (elevators2.get(1).getY() >= 263 && elevators2.get(1).getY() <= 293) {

            batch.draw(elevatorWire, elevators2.get(1).getX() + 90, elevators2.get(1).getY() + 80, 10, 110);

        }

        if (elevators2.get(1).getY() >= 294 && elevators2.get(1).getY() <= 330) {

            batch.draw(elevatorWire, elevators2.get(1).getX() + 90, elevators2.get(1).getY() + 80, 10, 80);

        }


        for (Obstacle o : o2) {

            o.draw(batch);
        }

        walls2.get(0).draw(batch);

        spike.get(0).draw(batch);

        checkWallCollision();
        checkSpikeCollision();


        batch.draw(elevatorWire, elevators2.get(2).getX() + 90, elevators2.get(2).getY() + 60, 10, 700);


        for (Elevator elevator2 : elevators2) {
            elevator2.updatePositionFromSpeed();
            elevator2.draw(batch);
        }


        for (Door d : d2) {
            d.draw(batch);
        }

        if (keys2.get(0).getHasKey() == false) {

            keys2.get(0).draw(batch);
        }


        exitDoors.get(1).draw(batch);

        //ritar ut healthPack
        for (int i = 0; i < healthPacks2.size(); i++) {
            healthPacks2.get(i).draw(batch);
        }

        // draw player bullet

        var1 = this.bullets.iterator();

        while (var1.hasNext()) {
            enemy1 = (Bullet) var1.next();

            if (enemy1 != null) {
                enemy1.draw(this.batch);
            }
        }


        for (Player player : players) {
            checkObstacleCollision(player);

            checkElevatorOverLap(player);
            enterDoor(player);
            player.updatePositionFromSpeed();
            player.draw(batch);
        }

        while (var1.hasNext()) {
            enemy = (Player) var1.next();


            enemy.updatePositionFromSpeed();
            enemy.draw(batch);
        }

        var1 = enemies.iterator();

        while (var1.hasNext()) {


            com.mygdx.game.Enemy enemy2 = (com.mygdx.game.Enemy) var1.next();

            enemy2.draw(batch);
        }


        //ritar ut enemyBullets
        for (Bullet enemyBullet : enemyBullets) {
            if (enemyBullet != null) {

                enemyBullet.updatePositionFromSpeed();
                enemyBullet.draw(batch);
            }
        }

        drawHpBar();
        batch.end();

    }

    public void renderLevel3() {

        checkInput();
        checkWallCollision();
        enterDoor(players.get(0));
        playerLifeCheck();
        bulletLogic();
        healthPackCollision();
        checkPlayerXMoveTowards();


        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        if (onceLevel3 == false) {

            bullets.clear();
            enemies.clear();

            createActors3();


            players.get(0).sprite.setPosition(30, 490);
            onceLevel3 = true;
        }

        keyCheck();


        if (firstBossLevel3 == false) {

            bossCounterLevel3 = bossCounterLevel3 + 0.016f;

            if (bossCounterLevel3 > 2.2f) {
                firstBossLevel3 = true;
            }
        }

        if (firstBossLevel3 == true && onceLevel3_2 == false) {

            com.mygdx.game.Enemy l31 = new Enemy("s1.png", 650, 600, 50, 400);
            enemies.add(l31);

            onceLevel3_2 = true;


        }

        if (onceLevel3_3 == false && players.get(0).getY() <= 150) {

            com.mygdx.game.Enemy l32 = new Enemy("s1.png", 650, 50, 50, 400);
            enemies.add(l32);

            com.mygdx.game.Enemy l33 = new Enemy("s1.png", 100, 50, 50, 400);
            enemies.add(l33);

            onceLevel3_3 = true;


        }

        if (onceLevel3_4 == false && players.get(0).getX() < 400 && players.get(0).getY() <= 190) {


            com.mygdx.game.Enemy l34 = new Enemy("s1.png", 600, 190, 50, 400);
            enemies.add(l34);

            onceLevel3_4 = true;


        }

        if (onceLevel3_5 == false && onceLevel3_4 == true && onceLevel3_3 == true && players.get(0).getY() > 320) {

            com.mygdx.game.Enemy l35 = new Enemy("s1.png", 150, 350, 50, 400);
            enemies.add(l35);

            com.mygdx.game.Enemy l36 = new Enemy("s1.png", 300, 350, 50, 400);
            enemies.add(l36);

            onceLevel3_5 = true;
        }

        if (keys3.get(0).getHasKey() == true && onceLevel3_6 == false) {

            com.mygdx.game.Enemy l36 = new Enemy("s1.png", 330, 350, 50, 600);
            enemies.add(l36);

            onceLevel3_6 = true;


        }


        players.get(0).duckSwitchOn();

        if (jumpcheck == false) {
            players.get(0).jumpSwitchOff(); // switch (off) for rasing x speed while jumping


            if (duckCheck == false) {
                players.get(0).jumpImage();
            }

        }


        if (jumpcheck == true) {
            // Need to fix sizes on jump images
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

        checkWallCollisionEnemy();

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


        for (com.mygdx.game.Enemy e : enemies) {
            e.updatePositionFromSpeed();
            e.setSpeedY(-2);
            checkObstacleCollisionEnemies(e);
        }


        for (Bullet bullet1 : bullets) {
            if (bullet1 != null) {
                for (com.mygdx.game.Enemy e : enemies) {
                    bullet1.updatePositionFromSpeed();
                    checkBulletCollision(bullet1);
                    break;
                }
            }
        }

        // Tar bort player bullet
        for (Bullet bullet : bullets) {
            if (bullet.getX() <= 0 || bullet.getX() >= 1280) {
                bullets.remove(bullet);
                break;
            }
        }

        checkEnemyCollision();


        // Elevator leftmost


        // floor 2


        if (elevators3.get(1).getY() == 190f && counterElevator4 < 4.8f) {

            elevators3.get(1).setSpeedY(0);
            if (elevatorStopped == true) {
                elevatorSound.play();
                elevatorStopped = false;
            }
            counterElevator4 = counterElevator4 + 0.016f;


            if (counterElevator4 > 4.8f)
                elevators3.get(1).startElevatorBottom();
            elevatorUp4 = true;
        }


        //floor 3
        if (elevators3.get(1).getY() >= 340 && counterElevator4 < 4.8f) {

            elevators3.get(1).setSpeedY(0);
            if (elevatorStopped == true) {
                elevatorSound.play();
                elevatorStopped = false;
            }
            counterElevator4 = counterElevator4 + 0.016f;

            if (counterElevator4 > 4.8f)
                elevators3.get(1).startElevatorTop();
            elevatorUp4 = false;
        }

        if (counterElevator4 >= 4.8f) {
            if (elevatorUp4 == false) {
                elevators3.get(1).setSpeedY(-2);
            } else if (elevatorUp4 == true) {
                elevators3.get(1).setSpeedY(2);
            }

            counterElevator4 = 0;
            elevatorStopped = true;
        }


        //Elevator right most

        o3.get(15).sprite.setPosition(elevators3.get(0).getX(), elevators3.get(0).getY() + 105);
        o3.get(15).sprite.setColor(0f);


        // floor 1

        if (elevators3.get(0).getY() == 50f && counterElevator5 < 4.8f) {

            elevators3.get(0).setSpeedY(0);
            if (elevatorStopped == true) {
                elevatorSound.play();
                elevatorStopped = false;
            }
            counterElevator5 = counterElevator5 + 0.016f;


            if (counterElevator5 > 4.8f && keys3.get(0).getHasKey() == false) {
                elevators3.get(0).startElevatorBottom();

            }
            elevatorUp5 = true;
        }


        // floor 4


        if (keys3.get(0).getHasKey() == false) {

            if (elevators3.get(0).getY() >= 480 && counterElevator5 < 4.8f) {

                elevators3.get(0).setSpeedY(0);
                if (elevatorStopped == true) {
                    elevatorSound.play();
                    elevatorStopped = false;
                }
                counterElevator5 = counterElevator5 + 0.016f;

                if (counterElevator5 > 4.8f)
                    elevators3.get(0).startElevatorTop();
                elevatorUp5 = false;
            }


            if (counterElevator5 >= 4.8f) {
                if (elevatorUp5 == false) {
                    elevators3.get(0).setSpeedY(-2);
                } else if (elevatorUp5 == true) {
                    elevators3.get(0).setSpeedY(2);
                }

                counterElevator5 = 0;
                elevatorStopped = true;
            }
        }

        if (elevators3.get(0).getY() == 50 && keys3.get(0).getHasKey() == true) {
            wrongFloor = true;
        }

        if (keys3.get(0).getHasKey() == true & wrongFloor == false && elevators3.get(0).getY() >= 480) {

            elevators3.get(0).startElevatorTop();
            wrongFloor = true;
        }

        if (elevators3.get(0).getY() >= 1400) {
            gameStatus = gameStatus.FINISH;
        }

        batch.draw(space, 0, 0, 1280, 720);

        walls3.get(0).draw(batch);


        spikes2.get(0).draw(batch);


        // elevators3.get(1) ElevatorWire logic


        if (elevators3.get(1).getY() >= 190 && elevators3.get(1).getY() <= 210) {


            batch.draw(elevatorWire, elevators3.get(1).getX() + 50, elevators3.get(1).getY() + 80, 10, 190);
        }


        if (elevators3.get(1).getY() >= 211 && elevators3.get(1).getY() <= 231) {

            batch.draw(elevatorWire, elevators3.get(1).getX() + 50, elevators3.get(1).getY() + 80, 10, 160);

        }

        if (elevators3.get(1).getY() >= 232 && elevators3.get(1).getY() <= 252) {

            batch.draw(elevatorWire, elevators3.get(1).getX() + 50, elevators3.get(1).getY() + 80, 10, 130);

        }

        if (elevators3.get(1).getY() >= 253 && elevators3.get(1).getY() <= 273) {

            batch.draw(elevatorWire, elevators3.get(1).getX() + 50, elevators3.get(1).getY() + 80, 10, 100);

        }

        if (elevators3.get(1).getY() >= 274 && elevators3.get(1).getY() <= 294) {

            batch.draw(elevatorWire, elevators3.get(1).getX() + 50, elevators3.get(1).getY() + 80, 12, 80);

        }

        if (elevators3.get(1).getY() >= 295 && elevators3.get(1).getY() <= 340) {

            batch.draw(elevatorWire, elevators3.get(1).getX() + 50, elevators3.get(1).getY() + 80, 12, 60);

        }

        for (int i = 0; i < healthPacks3.size(); i++) {
            healthPacks3.get(i).draw(batch);
        }


        for (Elevator e : elevators3) {

            e.updatePositionFromSpeed();
            e.draw(batch);


        }


        for (Door door : doors3) {

            door.draw(batch);
        }

        if (keys3.get(0).getHasKey() == false) {
            keys3.get(0).draw(batch);
        }


        var1 = this.bullets.iterator();

        while (var1.hasNext()) {
            enemy1 = (Bullet) var1.next();

            if (enemy1 != null) {
                enemy1.draw(this.batch);
            }
        }


        for (Obstacle o : o3) {

            o.draw(batch);

        }

        if (keys3.get(0).getHasKey() == true) {
            batch.draw(exitSign, 1050, 620, 60, 30);
        }


        if (keys3.get(0).getHasKey() == true && players.get(0).getPlayerRectangle().overlaps(elevators3.get(0).getElevatorRectangle())) {
            explo.get(0).sprite.setPosition(elevators3.get(0).getX(), elevators3.get(0).getY() - 50);
            explo.get(1).sprite.setPosition(elevators3.get(0).getX() + 50, elevators3.get(0).getY() - 50);
            explo.get(0).draw(batch);
            explo.get(1).draw(batch);
        }


        for (Player player : players) {
            player.updatePositionFromSpeed();
            if (jumpcheck == false) {
                // Set gravity for player to -1 when the jump has ended
                player.setSpeedY(-2);
                checkElevatorOverLap(player);
            }
            checkObstacleCollision(player);
        }

        checkSpikeCollision();


        while (var1.hasNext()) {
            enemy = (Player) var1.next();


            enemy.updatePositionFromSpeed();
            enemy.draw(batch);//}
        }

        var1 = enemies.iterator();

        while (var1.hasNext()) {


            com.mygdx.game.Enemy enemy2 = (com.mygdx.game.Enemy) var1.next();

            enemy2.draw(batch);
        }


        //ritar ut enemyBullets
        for (Bullet enemyBullet : enemyBullets) {
            if (enemyBullet != null) {

                enemyBullet.updatePositionFromSpeed();
                enemyBullet.draw(batch);
            }
        }


        players.get(0).draw(batch);

        drawHpBar();
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

    public void renderInstructions() {
        Gdx.gl.glClearColor(50, 50, 50, 50);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(instScreen, 0, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            gameStatus = gameStatus.MENU;
        }
        batch.end();
    }

    public void renderFinish() {

        Gdx.gl.glClearColor(50, 50, 50, 50);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(gameDone, 0, 0);
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gameStatus = gameStatus.MENU;
        }
        batch.end();


    }

    public void renderLevelClear() {

        if (guitarSoundPlaying == false) {
            guitarLoop.play();
            guitarSoundPlaying = true;
        }
        // level clear bild vid här,

        Gdx.gl.glClearColor(50, 50, 50, 50);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(LevelClear, 0, 0);
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gameStatus = gameStatus.LEVEL2;
        }
        batch.end();

    }

    public void renderLevelClear2() {

        if (guitarSoundPlaying == true) {
            guitarLoop.play();
            guitarSoundPlaying = false;
        }
        // level clear bild vid här,

        Gdx.gl.glClearColor(50, 50, 50, 50);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(LevelClear, 0, 0);
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gameStatus = gameStatus.LEVEL3;
        }
        batch.end();


    }


    public void checkObstacleCollision(Player player) {

        if (gameStatus == gameStatus.LEVEL1) {

            for (Obstacle obstacle : obstacles) {
                if (player.getPlayerRectangle().overlaps(obstacle.getCollisionRectangle()))
                    player.setSpeedY(0);
            }
        }
        if (gameStatus == gameStatus.LEVEL2) {
            for (Obstacle obstacle : o2) {
                if (player.getPlayerRectangle().overlaps(obstacle.getCollisionRectangle()))
                    player.setSpeedY(0);
            }
        }


        if (gameStatus == gameStatus.LEVEL3) {
            for (Obstacle obstacle : o3) {
                if (player.getPlayerRectangle().overlaps(obstacle.getCollisionRectangle()))
                    player.setSpeedY(0);
            }
        }


    }


    public void checkObstacleCollisionEnemies(com.mygdx.game.Enemy enemy) {

        if (gameStatus == gameStatus.LEVEL1) {

            for (Obstacle obstacle : obstacles) {
                if (enemy.getEnemieRectangle().overlaps(obstacle.getCollisionRectangle()))
                    enemy.setSpeedY(0);
            }
        }

        if (gameStatus == gameStatus.LEVEL2) {

            for (Obstacle obstacle : o2) {

                if (enemy.getEnemieRectangle().overlaps(obstacle.getCollisionRectangle()))
                    enemy.setSpeedY(0);
            }
        }


        if (gameStatus == gameStatus.LEVEL3) {

            for (Obstacle obstacle : o3) {

                if (enemy.getEnemieRectangle().overlaps(obstacle.getCollisionRectangle()))
                    enemy.setSpeedY(0);
            }
        }


    }


    public void checkBulletCollision(Bullet bullet1) {
        for (com.mygdx.game.Enemy e : enemies) {
            if (bullet1.getBulletRectangle().overlaps(e.getEnemieRectangle())) {
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


    public void checkEnemyCollision() {
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

    public void enterDoor(Player player) {


        if (gameStatus == gameStatus.LEVEL1) {

            if (player.getPlayerRectangle().overlaps(doors.get(0).getCollisionRectangle())
                    && Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                player.setX(doors.get(1).getX());
                player.setY(doors.get(1).getY());
                doorSound.play();
            } else if (player.getPlayerRectangle().overlaps(doors.get(1).getCollisionRectangle())
                    && Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                player.setX(doors.get(0).getX());
                player.setY(doors.get(0).getY());
                doorSound.play();
            }
        }

        if (gameStatus == gameStatus.LEVEL2) {

            if (player.getPlayerRectangle().overlaps(d2.get(0).getCollisionRectangle()) && Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                player.setX(d2.get(1).getX());
                player.setY(d2.get(1).getY());
                doorSound.play();
            } else if (player.getPlayerRectangle().overlaps(d2.get(1).getCollisionRectangle()) && Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                player.setX(d2.get(0).getX());
                player.setY(d2.get(0).getY());
                doorSound.play();
            }


            if (player.getPlayerRectangle().overlaps(d2.get(2).getCollisionRectangle()) && Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                player.setX(d2.get(3).getX());
                player.setY(d2.get(3).getY());
                doorSound.play();
            } else if (player.getPlayerRectangle().overlaps(d2.get(3).getCollisionRectangle()) && Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                player.setX(d2.get(2).getX());
                player.setY(d2.get(2).getY());
                doorSound.play();
            }
        }


        if (gameStatus == gameStatus.LEVEL3) {

            if (player.getPlayerRectangle().overlaps(doors3.get(0).getCollisionRectangle()) && Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                player.setX(doors3.get(1).getX());
                player.setY(doors3.get(1).getY());
                doorSound.play();
            } else if (player.getPlayerRectangle().overlaps(doors3.get(1).getCollisionRectangle()) && Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                player.setX(doors3.get(0).getX());
                player.setY(doors3.get(0).getY());
                doorSound.play();
            }
        }


    }

    public void checkElevatorOverLap(Player player) {

        if (gameStatus == gameStatus.LEVEL1) {
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


        if (gameStatus == gameStatus.LEVEL2) {
            for (Elevator elevator : elevators2) {

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


        if (gameStatus == gameStatus.LEVEL3) {
            for (Elevator elevator : elevators3) {

                if (elevator.getSpeedY() > 0 && player.getPlayerRectangle().overlaps(o3.get(15).getCollisionRectangle())) {

                    players.get(0).setY(o3.get(15).sprite.getY());
                }


                if (players.get(0).getPlayerRectangle().overlaps(elevators3.get(0).getElevatorRectangle()) &&
                        keys3.get(0).getHasKey() == true) {
                    elevators3.get(0).setSpeedY(6);
                    explossionCounter = explossionCounter + 0.016f;
                    explossionQ = explo.get(0).animation(explossionCounter);
                    explo.get(1).animation(explossionCounter);


                    if (rocketSoundPlaying == false) {
                        rocketSound.play();
                        rocketSoundPlaying = true;
                    }

                    if (explossionQ == true) {
                        explossionCounter = 0;
                    }


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
    }


    public void exitDoorCheck() {

        if (gameStatus == gameStatus.LEVEL2) {
            if (players.get(0).getPlayerRectangle().overlaps(exitDoors.get(1).getCollisionRectangle())
                    && keys2.get(0).getHasKey() == true && Gdx.input.isKeyJustPressed(Input.Keys.E)) {

                gameStatus = gameStatus.LEVELCLEAR2;
            }
        }


        if (gameStatus == gameStatus.LEVEL1) {

            if (players.get(0).getPlayerRectangle().overlaps(exitDoors.get(0).getCollisionRectangle())
                    && keys.get(0).getHasKey() == true && Gdx.input.isKeyJustPressed(Input.Keys.E)) {

                gameStatus = gameStatus.LEVELCLEAR;

            }
        }
        if (gameStatus == gameStatus.LEVEL2) {

            if (players.get(0).getPlayerRectangle().overlaps(exitDoors.get(0).getCollisionRectangle())
                    && keys.get(0).getHasKey() == true && Gdx.input.isKeyJustPressed(Input.Keys.E)) {

                gameStatus = gameStatus.LEVELCLEAR2;

            }
        }
    }


    public void keyCheck() {

        if (gameStatus == gameStatus.LEVEL1) {

            if (players.get(0).getPlayerRectangle().overlaps(keys.get(0).getKeyRectangle())) {
                keys.get(0).setKeyTrue();

                if (keySoundPlaying == false) {
                    keySound.play();
                    keySoundPlaying = true;
                }
            }
        }

        if (gameStatus == gameStatus.LEVEL2) {
            if (players.get(0).getPlayerRectangle().overlaps(keys2.get(0).getKeyRectangle())) {
                keys2.get(0).setKeyTrue();

                if (keySoundPlaying == true) {
                    keySound.play();
                    keySoundPlaying = false;
                }
            }
        }


        if (gameStatus == gameStatus.LEVEL3) {
            if (players.get(0).getPlayerRectangle().overlaps(keys3.get(0).getKeyRectangle())) {
                keys3.get(0).setKeyTrue();

                if (keySoundPlaying == false) {
                    keySound.play();
                    keySoundPlaying = true;
                }
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

    public void checkSpikeCollision() {

        if (gameStatus == gameStatus.LEVEL2) {

            if (players.get(0).getPlayerRectangle().overlaps(spike.get(0).getCollisionRectangle())) {

                players.get(0).SetHP(0);
            }
        }


        if (gameStatus == gameStatus.LEVEL3) {

            if (players.get(0).getPlayerRectangle().overlaps(spikes2.get(0).getCollisionRectangle())) {


                players.get(0).SetHP(0);


            }
        }


    }

    public void healthPackCollision() {

        if (gameStatus == gameStatus.LEVEL2) {

            for (int j = 0; j < healthPacks2.size(); j++) {
                if (players.get(0).getPlayerRectangle().overlaps(healthPacks2.get(0).getCollisionRectangle())) {
                    healthPacks2.remove(0);
                    players.get(0).SetHP(100);

                }
            }
        }

        if (gameStatus == gameStatus.LEVEL3) {
            for (int j = 0; j < healthPacks3.size(); j++) {
                if (players.get(0).getPlayerRectangle().overlaps(healthPacks3.get(j).getCollisionRectangle())) {
                    healthPacks3.remove(j);
                    players.get(0).SetHP(100);
                }
            }
        }
    }


    public void checkWallCollision() {

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

        if (gameStatus == gameStatus.LEVEL2) {

            for (int i = 0; i < walls2.size(); i++) {
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                        && players.get(0).getPlayerRectangle().overlaps(walls2.get(i).getCollisionRectangle())) {
                    players.get(0).getSprite().translateX(-3);
                }
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT)
                        && players.get(0).getPlayerRectangle().overlaps(walls2.get(i).getCollisionRectangle())) {
                    players.get(0).getSprite().translateX(3);
                }
            }
        }


        if (gameStatus == gameStatus.LEVEL3) {

            for (int i = 0; i < walls3.size(); i++) {
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                        && players.get(0).getPlayerRectangle().overlaps(walls3.get(i).getCollisionRectangle())) {
                    players.get(0).getSprite().translateX(-3);
                }
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT)
                        && players.get(0).getPlayerRectangle().overlaps(walls3.get(i).getCollisionRectangle())) {
                    players.get(0).getSprite().translateX(3);
                }
            }
        }


    }

    public void checkWallCollisionEnemy() {

        for (com.mygdx.game.Enemy e : enemies) {


            if (gameStatus == gameStatus.LEVEL1) {

                for (int i = 0; i < walls.size(); i++) {
                    if (e.getSprite().isFlipX() == false && e.getSprite().isFlipY() == false
                            && e.getEnemieRectangle().overlaps(walls.get(i).getCollisionRectangle())) {
                        e.getSprite().translateX(-2);
                    }
                    if (e.getSprite().isFlipX() == true && e.getSprite().isFlipY() == false
                            && e.getEnemieRectangle().overlaps(walls.get(i).getCollisionRectangle())) {
                        e.getSprite().translateX(2);
                    }
                }


            }

            if (gameStatus == gameStatus.LEVEL2) {

                for (int i = 0; i < walls2.size(); i++) {
                    if (e.getSprite().isFlipX() == false && e.getSprite().isFlipY() == false
                            && e.getEnemieRectangle().overlaps(walls2.get(i).getCollisionRectangle())) {
                        e.getSprite().translateX(-2);
                    }
                    if (e.getSprite().isFlipX() == true && e.getSprite().isFlipY() == false
                            && e.getEnemieRectangle().overlaps(walls2.get(i).getCollisionRectangle())) {
                        e.getSprite().translateX(2);
                    }
                }
            }


            if (gameStatus == gameStatus.LEVEL3) {

                for (int i = 0; i < walls3.size(); i++) {
                    if (e.getSprite().isFlipX() == false && e.getSprite().isFlipY() == false
                            && e.getEnemieRectangle().overlaps(walls3.get(i).getCollisionRectangle())) {
                        e.getSprite().translateX(-2);
                    }
                    if (e.getSprite().isFlipX() == true && e.getSprite().isFlipY() == false
                            && e.getEnemieRectangle().overlaps(walls3.get(i).getCollisionRectangle())) {
                        e.getSprite().translateX(2);
                    }
                }
            }

        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {


        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


    @Override
    public void dispose() {
        batch.dispose();
        levelImg.dispose();

        if (gameStatus == gameStatus.LEVEL2) {

        }
    }
}

