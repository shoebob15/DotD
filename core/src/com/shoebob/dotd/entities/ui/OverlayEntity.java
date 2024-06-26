package com.shoebob.dotd.entities.ui;

import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.components.TextureComponent;
import com.shoebob.dotd.entities.Entity;
import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.systems.UISystem;

// a static entity that renders relative to the camera
public class OverlayEntity implements Entity {
    public TextureComponent texture;

    // represents a position relative to the top-right 0,0, not the world-space coordinate
    // always < 1, "percentage"
    // higher the value, the more right/up it goes
    public PositionComponent screenPos;

    // represents the actual game-space location of the overlay - used for rendering & math
    public PositionComponent gamePos;

    // will not render if hidden
    public boolean isHidden = false;

    @Override
    public void create() {
        texture = new TextureComponent();
        screenPos = new PositionComponent();
        gamePos = new PositionComponent();
    }

    @Override
    public void update(DotD game) {
        gamePos = UISystem.getOverlayPosition(this, game);
        if (!isHidden) {
            game.batch.draw(texture.texture, gamePos.x, gamePos.y, texture.texture.getWidth() / 2, texture.texture.getHeight() / 2);
        }
    }

    @Override
    public void dispose() {
        texture.texture.dispose();
    }
}
