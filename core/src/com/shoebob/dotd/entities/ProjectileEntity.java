package com.shoebob.dotd.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class ProjectileEntity extends Entity {
    Vector2 velocity;

    public ProjectileEntity(float x, float y, Texture texture) {
        super(x, y, texture);
    }

    @Override
    public void update() {
        addVector(velocity);
    }
}
