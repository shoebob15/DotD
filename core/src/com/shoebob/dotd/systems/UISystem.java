package com.shoebob.dotd.systems;

import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.entities.ui.OverlayEntity;
import com.shoebob.dotd.game.DotD;

// system for working with UI Entities because Scene2D is a joke
public class UISystem {
    // returns game-space position given an overlay entity
    public static PositionComponent getOverlayPosition(OverlayEntity entity, DotD game) {
        // position the coordinate to the bottom left of the screen
        Vector2 pos = new Vector2(game.camera.position.x - (game.camera.viewportWidth / 2),
                game.camera.position.y - (game.camera.viewportHeight / 2)
        );

        pos.x += game.camera.viewportWidth * entity.screenPos.x;
        pos.y += game.camera.viewportHeight * entity.screenPos.y;

        return LocationSystem.vectorToPos(pos);
    }
}
