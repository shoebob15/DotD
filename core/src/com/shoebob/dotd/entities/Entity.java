package com.shoebob.dotd.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    protected float x, y, width, height;
    protected Texture texture;
    protected Vector2 vector;

    public Entity(float x, float y, float width, float height, Vector2 vector, Texture texture) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.vector = vector;
        this.texture = texture;
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

    public double getDirection() {
        return Math.toDegrees(Math.atan2(vector.y, vector.x));
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
