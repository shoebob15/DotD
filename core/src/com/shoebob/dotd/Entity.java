package com.shoebob.dotd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {
    protected float x, y;
    private Texture texture;

    public Entity(float x, float y, Texture texture) {
        this.x = x;
        this.y = y;
        this.texture = texture;
    }

    public Entity(Texture texture) {
        this.x = 0;
        this.y = 0;
        this.texture = texture;
    }

    public Entity() {
        this.x = 0;
        this.y = 0;
        this.texture = new Texture(Gdx.files.internal("util/broken_texture.png"));
    }

    public abstract void update();

    public abstract void draw(SpriteBatch s);

    public void dispose() {
        texture.dispose();
    }
}
