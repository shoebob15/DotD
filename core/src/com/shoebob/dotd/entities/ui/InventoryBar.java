package com.shoebob.dotd.entities.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

// class for the bar at the bottom of the screen that has spells, weapons, etc.
public class InventoryBar extends OverlayEntity {
    @Override
    public void create() {
        super.create();
        texture.texture = new Texture(Gdx.files.internal("ui/inventory.png"));
        screenPos.x = 0.5f;
        screenPos.y = 0.95f;
    }
}
