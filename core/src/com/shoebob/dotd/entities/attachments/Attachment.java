package com.shoebob.dotd.entities.attachments;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.components.TextureComponent;
import com.shoebob.dotd.entities.Entity;
import com.shoebob.dotd.game.DotD;

// renders a texture on a player's hand
public abstract class Attachment implements Entity {
    public TextureComponent texture;
    public PositionComponent position;

    protected long cooldown = 2000;
    protected long lastAttack = 0;

    @Override
    public void create() {
        texture = new TextureComponent();
        position = new PositionComponent();
    }

    public void draw(SpriteBatch s) {
        s.draw(texture.texture, position.x, position.y);
    }

    public void draw(SpriteBatch s, float rotation) {
        s.draw(texture.texture, position.x + 8, position.y + 8, 8, 8,
                8, 8, 2, 2, rotation, 0, 0, texture.texture.getWidth(),
                texture.texture.getHeight(), false, false);
    }


    // will return true if NOT on cooldown, else return false
    // also will reset cooldown
    protected boolean cooldown() {
        long time = System.currentTimeMillis();
        if (time > lastAttack + cooldown) {
            lastAttack = time;
            return true;
        }
        return false;
    }

    public abstract void use(DotD game);

    @Override
    public void dispose() {
        texture.texture.dispose();
    }
}
