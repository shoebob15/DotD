package com.shoebob.dotd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class PlayerAttachment {
    private Texture texture; // texture
    protected float x, y; // position
    private float toRotate = 0; // compounds on top of player animation rotation
    private boolean doneSwinging = true; // swinging animation is done
    private long swingDuration; // how long it takes to swing
    private long time, elapTime; // stores time for swing

    public PlayerAttachment(float x, float y, Texture texture, long swingDuration) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.swingDuration = swingDuration;
    }

    public void draw(SpriteBatch s, float rotation) {
        s.draw(texture, x + 8, y + 8, (float) texture.getWidth() / 2, (float) texture.getHeight() / 2,
                8, 8, 2, 2, rotation + toRotate, 0, 0, texture.getWidth(),
                texture.getHeight(), false, false);
        time = TimeUtils.millis();
    }

    public void setVectorLocation(Vector2 vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public void swing() {
        if (TimeUtils.timeSinceMillis(5000) > 0) {

        }
    }
}
