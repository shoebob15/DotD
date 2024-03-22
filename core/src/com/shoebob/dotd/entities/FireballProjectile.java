package com.shoebob.dotd.entities;

import com.shoebob.dotd.DotDGame;

public class FireballProjectile extends ProjectileEntity {
    @Override
    public void create() {
        super.create();
        position = DotDGame.player.position;
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
