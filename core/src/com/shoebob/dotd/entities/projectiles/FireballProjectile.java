package com.shoebob.dotd.entities.projectiles;

import com.shoebob.dotd.DotDGame;
import com.shoebob.dotd.util.Consts;

public class FireballProjectile extends AnimatedProjectile {
    @Override
    public void create() {
        super.create();
        position = DotDGame.player.position;
        animationComponent = Consts.AnimationComponents.fireball;
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
