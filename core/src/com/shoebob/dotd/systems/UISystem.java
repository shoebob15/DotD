package com.shoebob.dotd.systems;

import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.DotDGame;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.entities.ui.OverlayEntity;

// system for working with UI Entities because Scene2D is a joke
public class UISystem {
    // returns game-space position given an overlay entity
    public static PositionComponent getOverlayPosition(OverlayEntity entity) {
        // position the coordinate to the bottom left of the screen
        Vector2 pos = new Vector2(DotDGame.camera.position.x - (DotDGame.camera.viewportWidth / 2),
                DotDGame.camera.position.y - (DotDGame.camera.viewportHeight / 2)
        );

        pos.x += DotDGame.camera.viewportWidth * entity.screenPos.x;
        pos.y += DotDGame.camera.viewportHeight * entity.screenPos.y;

        return LocationSystem.vectorToPos(pos);
    }
}
