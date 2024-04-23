package com.shoebob.dotd.entities;

import com.shoebob.dotd.components.TextureComponent;

// a static entity that renders relative to the camera
public class OverlayEntity implements Entity {
    TextureComponent texture;
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
