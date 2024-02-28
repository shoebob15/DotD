package com.shoebob.dotd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

// renders a texture on a player's hand
public abstract class Attachment {
    private Texture texture; // texture
    protected float x, y; // position

    public Attachment(float x, float y, Texture texture) {
        this.x = x;
        this.y = y;
        this.texture = texture;}

    public void draw(SpriteBatch s) {
        s.draw(texture, x, y);
    }

    public void draw(SpriteBatch s, float rotation) {
        s.draw(texture, x + 8, y + 8, 8, 8,
                8, 8, 2, 2, rotation, 0, 0, texture.getWidth(),
                texture.getHeight(), false, false);
    }

    public void setVectorLocation(Vector2 vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public abstract void use();
}
