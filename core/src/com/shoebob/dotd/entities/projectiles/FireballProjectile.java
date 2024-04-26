package com.shoebob.dotd.entities.projectiles;

import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.systems.LocationSystem;
import com.shoebob.dotd.game.Consts;


// TODO: shouldn't be its own class
public class FireballProjectile extends AnimatedProjectile {
    @Override
    public void create() {
        super.create();

        animationComponent = Consts.AnimationComponents.fireball;
    }

    @Override
    public void update(DotD game) {
        LocationSystem.addVelocity(position, velocity);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
