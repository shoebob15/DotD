package com.shoebob.dotd.entities.projectiles;

import com.shoebob.dotd.components.AnimationComponent;

public class AnimatedProjectile extends ProjectileEntity {
    public AnimationComponent animationComponent;

    @Override
    public void create() {
        super.create();
        animationComponent = new AnimationComponent();
    }
}
