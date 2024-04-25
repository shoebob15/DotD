package com.shoebob.dotd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.shoebob.dotd.game.DotD;

public class MainMenuScreen implements Screen {
    final DotD game;

    OrthographicCamera camera;

    public MainMenuScreen(final DotD game) {
        this.game = game;

        camera = new OrthographicCamera(300,
                300 * ((float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth())
        );
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "DotD", 100, 150);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new GameScreen(game));
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

    }
}
