package com.shoebob.dotd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shoebob.dotd.entities.MagicProjectileEntity;

// used for wands, staffs, etc.
public class MagicAttachment extends Attachment {
    private MagicProjectileEntity projectile;

    public MagicAttachment(float x, float y, Texture texture, MagicProjectileEntity projectile) {
        super(x, y, texture);
        this.projectile = projectile;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(SpriteBatch s) {
        super.draw(s);
        projectile.draw(s);
    }

    public void use() {
        summonProjectile();
    }

    private void summonProjectile() {

    }

    @Override
    public String toString() {
        return "MagicAttachment{" +
                "projectile=" + projectile +
                "} " + super.toString();
    }
}
