package com.shoebob.dotd.entities.projectiles;

import com.shoebob.dotd.DotDGame;
import com.shoebob.dotd.systems.LocationSystem;
import com.shoebob.dotd.util.Consts;

public class FireballProjectile extends AnimatedProjectile {
    @Override
    public void create() {
        super.create();
        position = LocationSystem.clonePos(DotDGame.player.position);

        animationComponent = Consts.AnimationComponents.fireball;
    }

    @Override
    public void update() {
        super.update();
        LocationSystem.addVelocity(position, velocity);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
