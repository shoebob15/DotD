package com.shoebob.dotd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
        ScreenUtils.clear(.2f, 0, 0, 0);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Depths of", -100, 75);
        game.font.draw(game.batch, "The Dungeon", -135, 40);
        game.font.draw(game.batch, "PLAY", -50, -40);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            if (x > 213 && x < 444 && y > 325 && y < 370) {
                game.setScreen(new GameScreen(game));
            }
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
