package com.shoebob.dotd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class PlayerAttachment {
    private Texture texture;
    protected float x, y;

    public PlayerAttachment(float x, float y, Texture texture) {
        this.x = x;
        this.y = y;
        this.texture = texture;
    }

    public void draw(SpriteBatch s) {
        s.draw(texture, x, y, 0, 0, 8, 8, 2, 2, 0f, 0, 0,
                16, 16, false, false);
    }

    public void draw(SpriteBatch s, float rotation) {
        s.draw(texture, x + 8, y + 8, (float) texture.getWidth() / 2, (float) texture.getHeight() / 2,
                8, 8, 2, 2, rotation, 0, 0, texture.getWidth(),
                texture.getHeight(), false, false);
    }

    public void setVectorLocation(Vector2 vector) {
        this.x = vector.x;
        this.y = vector.y;
    }
}
