package com.shoebob.dotd.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.components.TextureComponent;
import com.shoebob.dotd.components.VelocityComponent;

public class ProjectileEntity implements Entity {
    public PositionComponent position;
    public VelocityComponent velocity;
    public TextureComponent texture;

    @Override
    public void create() {
        position = new PositionComponent();
        velocity = new VelocityComponent();
        texture = new TextureComponent();
    }

    @Override
    public void update() {

    }

    @Override
    public void dispose() {

    }
}
