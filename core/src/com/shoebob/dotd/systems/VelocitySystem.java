package com.shoebob.dotd.systems;

import com.badlogic.gdx.math.Vector2;

public class VelocitySystem {

    // returns a multiplied vector by the given factor
    public static Vector2 mulVec(Vector2 vec, float fac) {
        return new Vector2(vec.x *  fac, vec.y * fac);
    }
}
