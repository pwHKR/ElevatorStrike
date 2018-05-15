package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InfoScreen implements Screen {
    private Texture information;
    private SpriteBatch batch;
    private MyGdxGame game;

    public InfoScreen(MyGdxGame game) {
        this.game = game;
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        information = new Texture("information.jpg"); // måste ändras till bättre :)
    }

    @Override
    public void render(float delta) {
        handleInput();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(information, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.end();
    }

    private void handleInput()
    {
        if(Gdx.input.justTouched())
        {
            game.setScreen(new SplashScreen(game));
            dispose();

        }
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
        information.dispose();
    }
}
