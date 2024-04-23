package com.shoebob.dotd.entities.projectiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shoebob.dotd.components.AnimationComponent;
import com.shoebob.dotd.systems.AnimationSystem;

public class AnimatedProjectile extends ProjectileEntity {
    public AnimationComponent animationComponent;

    @Override
    public void draw(SpriteBatch s) {
        super.draw(s);

        float angle = velocity.vector.angleDeg();
        
        s.draw(AnimationSystem.getAnimationFrame(animationComponent),
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
    public void create() {
        super.create();
        animationComponent = new AnimationComponent();
    }
}