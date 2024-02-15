package com.shoebob.dotd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerAttachment {
    private Texture texture;
    protected float x, y;

    public PlayerAttachment(float x, float y, Texture texture) {
        this.x = x;
        this.y = y;
        this.texture = texture;
    }

    public void draw(SpriteBatch s) {
        s.draw(texture, x, y);
    }

    public void update(Player player) {
        x = player.x;
        y = player.y;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
