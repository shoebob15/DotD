package com.shoebob.dotd.entities.attachments;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.shoebob.dotd.DotDGame;
import com.shoebob.dotd.entities.projectiles.FireballProjectile;
import com.shoebob.dotd.util.CameraShake;

public class MagicStaffAttachment extends Attachment {
    private FireballProjectile projectile;


    @Override
    public void create() {
        super.create();
        projectile = new FireballProjectile();
        texture.texture = new Texture("weapons/magic_staff.png");
    }


    @Override
    public void update() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void use() {
        if (cooldown()) {

        }
    }
}
