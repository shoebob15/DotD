package com.shoebob.dotd.entities.ui;

import com.shoebob.dotd.DotDGame;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.components.TextureComponent;
import com.shoebob.dotd.entities.Entity;
import com.shoebob.dotd.systems.UISystem;

// a static entity that renders relative to the camera
public class OverlayEntity implements Entity {
    public TextureComponent texture;

    // represents a postion relative to the top-right 0,0, not the world-space coordinate
    public PositionComponent screenPos;

    // represents the actual game-space location of the overlay
    public PositionComponent gamePos;

    @Override
    public void create() {
        texture = new TextureComponent();
        screenPos = new PositionComponent();
        gamePos = new PositionComponent();
    }

    @Override
    public void update() {
        UISystem.setOverlayPosition(this);
        DotDGame.batch.draw(texture.texture, screenPos.x, screenPos.y);
    }

    @Override
    public void dispose() {

    }
}
