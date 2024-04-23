package com.shoebob.dotd.entities.ui;

import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.components.TextureComponent;
import com.shoebob.dotd.entities.Entity;

// a static entity that renders relative to the camera
public class OverlayEntity implements Entity {
    TextureComponent texture;

    // represents a postion relative to the top-right 0,0, not the world-space coordinate
    PositionComponent position;
    @Override
    public void create() {
        texture = new TextureComponent();
    }

    @Override
    public void update() {

    }

    @Override
    public void dispose() {

    }
}
