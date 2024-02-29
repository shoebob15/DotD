package com.shoebob.dotd.entities;

import com.badlogic.gdx.math.Vector2;

public class ProjectileEntity extends Entity {
    Vector2 velocity;

    @Override
    public void update() {
        addVector(velocity);
    }
}
