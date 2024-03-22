package com.shoebob.dotd.entities;

public class FireballProjectile extends ProjectileEntity {
    @Override
    public void create() {
        super.create();
        position = player.position;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
