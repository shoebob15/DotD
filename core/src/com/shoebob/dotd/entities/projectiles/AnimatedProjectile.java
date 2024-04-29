package com.shoebob.dotd.entities.projectiles;

import com.shoebob.dotd.components.AnimationComponent;
import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.systems.AnimationSystem;
import com.shoebob.dotd.systems.LocationSystem;

public class AnimatedProjectile extends ProjectileEntity {
    public AnimationComponent animationComponent;

    public AnimatedProjectile(AnimationComponent animationComponent) {
        this.animationComponent = animationComponent;
    }

    @Override
    public void draw(DotD game) {
        float angle = velocity.vector.angleDeg();
        
        game.batch.draw(AnimationSystem.getAnimationFrame(animationComponent, game),
                position.x,
                position.y,
                (float) animationComponent.animation.getKeyFrames()[0].getRegionWidth() / 2,
                (float) animationComponent.animation.getKeyFrames()[0].getRegionHeight() / 2,
                (float) animationComponent.animation.getKeyFrames()[0].getRegionWidth(),
                (float) animationComponent.animation.getKeyFrames()[0].getRegionHeight(),
                0.5F,
                0.5F,
                angle
        );
    }

    @Override
    public void update(DotD game) {
        LocationSystem.addVelocity(position, velocity);
    }

    @Override
    public void create() {
        super.create();
        animationComponent = new AnimationComponent();
    }

    public AnimatedProjectile copy() {
        return new AnimatedProjectile(animationComponent);
    }
}
