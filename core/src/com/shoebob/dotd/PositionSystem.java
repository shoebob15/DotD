package com.shoebob.dotd;

import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.components.VelocityComponent;

public class PositionSystem {
    // takes position and velocity, and adds velocity to position
    public void addVelocity(PositionComponent position, VelocityComponent velocity) {
        position.x += velocity.vector.x;
        position.y += velocity.vector.y;
    }

    // returns direction relative in coordinate position
    public float getDirection(VelocityComponent velocity) {
        return (float) Math.toDegrees(Math.atan2(velocity.vector.y, velocity.vector.x));
    }
}
