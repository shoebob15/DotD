package com.shoebob.dotd.systems;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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

    public static PositionComponent clonePos(PositionComponent pos) {
        PositionComponent temp = new PositionComponent();
        temp.x = pos.x;
        temp.y = pos.y;
        return temp;
    }

    public static PositionComponent vectorToPos(Vector2 vec) {
        PositionComponent temp = new PositionComponent();
        temp.x = vec.x;
        temp.y = vec.y;
        return temp;
    }

    // we can just ignore the z-axis in this situation, as we are working in a 2d space
    public static PositionComponent vectorToPos(Vector3 vec) {
        PositionComponent temp = new PositionComponent();
        temp.x = vec.x;
        temp.y = vec.y;
        return temp;
    }
}
