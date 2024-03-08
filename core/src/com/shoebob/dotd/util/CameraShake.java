package com.shoebob.dotd.util;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class CameraShake {
    private static float time = 0;
    private static float currentTime = 0;
    private static float magnitude = 0;
    private static float currentMagnitude = 0;
    private static Random random;
    private static Vector2 pos = new Vector2();

    public static void shake(float shakeMagnitude, float length) {
        random = new Random();
        magnitude = shakeMagnitude;
        time = length;
        currentTime = 0;
    }

    public static Vector2 tick(float delta) {
        if (currentTime <= time) {
            currentMagnitude = magnitude * ((time - currentTime) / time);

            pos.x = (random.nextFloat() - 0.5f) * 2 * currentMagnitude;
            pos.y = (random.nextFloat() - 0.5f) * 2 * currentMagnitude;

            currentTime += delta;
        } else {
            time = 0;
        }
        return pos;
    }

    public static float getTime() {
        return time;
    }

    public static Vector2 getPos() {
        return pos;
    }
}
