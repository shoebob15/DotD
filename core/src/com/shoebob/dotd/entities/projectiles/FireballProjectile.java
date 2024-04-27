package com.shoebob.dotd.entities.projectiles;

import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.systems.LocationSystem;
import com.shoebob.dotd.game.Consts;


// TODO: shouldn't be its own class
public class FireballProjectile extends AnimatedProjectile {
    // TODO: create() method stupid for inline object creation - probably refactor
    public FireballProjectile() {
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
