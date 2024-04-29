package com.shoebob.dotd.entities.projectiles;

import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.components.VelocityComponent;
import com.shoebob.dotd.entities.Entity;
import com.shoebob.dotd.game.DotD;

public abstract class ProjectileEntity implements Entity {
    public PositionComponent position;
    public VelocityComponent velocity;


    @Override
    public void create() {
        position = new PositionComponent();
        velocity = new VelocityComponent();
    }

    public abstract void draw(DotD game);
    @Override
    public abstract void update(DotD game);

    @Override
    public void dispose() {

    }
}
