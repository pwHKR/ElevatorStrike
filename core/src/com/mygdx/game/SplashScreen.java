package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Level.LevelFactory;

public class SplashScreen implements Screen {

    private SpriteBatch batch;
    private Texture background;
    private Texture titleText;
    private Texture screenText;
    private OrthographicCamera camera;
    private MyGdxGame game;


    public SplashScreen(MyGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1280,720);
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture("elevatorsplash.jpg");
        titleText = new Texture("cooltext.png");
        screenText = new Texture("splashscreentext.png");
    }

    @Override
    public void render(float delta) {
        handleInput();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(background,0,0);
        batch.draw(titleText, 300,500);
        batch.draw(screenText,400,100);
        batch.end();
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

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        screenText.dispose();
        titleText.dispose();

    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            game.setScreen(new LevelFactory(game));
            dispose();
        }
    }
}
