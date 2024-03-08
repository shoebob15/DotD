package com.shoebob.dotd.entities.attachments;

import com.badlogic.gdx.graphics.Texture;
import com.shoebob.dotd.entities.MagicProjectileEntity;
import com.shoebob.dotd.util.CameraShake;

public class MagicStaffAttachment extends MagicAttachment {
    public MagicStaffAttachment(float x, float y, Texture texture, MagicProjectileEntity projectile) {
        super(x, y, texture, projectile);
    }

    @Override
    public void update() {

    }

    @Override
    public void use() {
        CameraShake.shake(1, .2f);
    }
}
