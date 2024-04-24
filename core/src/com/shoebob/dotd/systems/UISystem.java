package com.shoebob.dotd.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.DotDGame;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.entities.ui.OverlayEntity;

public class UISystem {
    // returns game-space position given an overlay entity
    public static PositionComponent getOverlayPosition(OverlayEntity entity) {
        // calculate screen position based on entity location values
        Vector2 pos = new Vector2(Gdx.graphics.getWidth() * entity.screenPos.x,
                Gdx.graphics.getHeight() * entity.screenPos.y
        );

        pos.x += DotDGame.camera.position.x;
        pos.y += DotDGame.camera.position.y;

        return LocationSystem.vectorToPos(pos);
    }
}
