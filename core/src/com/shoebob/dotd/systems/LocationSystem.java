package com.shoebob.dotd.systems;

import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.components.VelocityComponent;

public class LocationSystem {
    // takes position and velocity, and adds velocity to position
    public static void addVelocity(PositionComponent position, VelocityComponent velocity) {
        position.x += velocity.vector.x;
        position.y += velocity.vector.y;
    }

    // returns direction in angles in standard coordinate position
    public static float getDirection(VelocityComponent velocity) {
        return (float) Math.toDegrees(Math.atan2(velocity.vector.y, velocity.vector.x));
    }
}
