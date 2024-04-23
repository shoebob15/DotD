package com.shoebob.dotd.entities.projectiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.components.TextureComponent;
import com.shoebob.dotd.components.VelocityComponent;
import com.shoebob.dotd.entities.Entity;

public class ProjectileEntity implements Entity {
    public PositionComponent position;
    public VelocityComponent velocity;


    @Override
    public void create() {
        position = new PositionComponent();
        velocity = new VelocityComponent();
    }

    public void draw(SpriteBatch s) {

    }
    @Override
    public void update() {

    }

    @Override
    public void dispose() {

    }
}