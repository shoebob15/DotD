package com.shoebob.dotd.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shoebob.dotd.entities.Player;
import com.shoebob.dotd.screens.MainMenuScreen;

public class DotD extends Game {
    // the spritebatch renderer
    public SpriteBatch batch;

    public BitmapFont font;

    public OrthographicCamera camera;

    public Player player;

    // stores the statetime
    public float statetime;


    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(new FileHandle("font.fnt"), new FileHandle("font.png"), false);
        player = new Player();
        player.create();

        camera = new OrthographicCamera(300, 300 * ((float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth()));

        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render(); // calls the render method of the current screen
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
