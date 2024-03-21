package com.shoebob.dotd.entities.attachments;

import com.shoebob.dotd.entities.ProjectileEntity;
import com.shoebob.dotd.util.CameraShake;

public class MagicStaffAttachment extends MagicAttachment {

    public MagicStaffAttachment(ProjectileEntity projectile) {
        super(projectile);
    }

    @Override
    public void update() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void use() {
        CameraShake.shake(1, .2f);
        System.out.println("use");
    }
}
