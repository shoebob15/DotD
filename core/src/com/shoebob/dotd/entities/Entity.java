package com.shoebob.dotd.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    protected float x, y;
    protected Texture texture;

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

    public void draw(SpriteBatch s) {
        s.draw(texture, x, y);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void addVector(Vector2 v) {
        x += v.x;
        y += v.y;
    }

    public void dispose() {
        texture.dispose();
    }

    @Override
    public String toString() {
        return "Entity{" +
                "x=" + x +
                ", y=" + y +
                ", texture=" + texture +
                '}';
    }
}
