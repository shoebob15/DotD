package com.shoebob.dotd.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.game.DotD;

// idc that this is bad practice
public class Util {
    // returns a world-space coordinate given a screen-coordinate
    public static PositionComponent screenToWorld(PositionComponent pos, DotD game) {
        Vector3 tmp = game.camera.unproject(new Vector3(new Vector2(pos.x, pos.y), 0));
        return new PositionComponent(tmp.x, tmp.y);
    }
}
