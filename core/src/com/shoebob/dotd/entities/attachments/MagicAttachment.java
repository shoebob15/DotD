package com.shoebob.dotd.entities.attachments;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shoebob.dotd.entities.MagicProjectileEntity;

// used for wands, staffs, etc.
public abstract class MagicAttachment extends Attachment {
    private MagicProjectileEntity projectile;

    public MagicAttachment(float x, float y, Texture texture, MagicProjectileEntity projectile) {
        this.projectile = projectile;
    }

    @Override
    public abstract void update();

    @Override
    public void draw(SpriteBatch s) {
        super.draw(s);
        s.draw(texture.texture, position.x, position.y);
    }

    public abstract void use();


    @Override
    public String toString() {
        return "MagicAttachment{" +
                "projectile=" + projectile +
                "} " + super.toString();
    }
}
