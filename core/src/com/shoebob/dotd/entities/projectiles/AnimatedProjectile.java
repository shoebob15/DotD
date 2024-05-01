package com.shoebob.dotd.entities.projectiles;

import com.shoebob.dotd.components.AnimationComponent;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.systems.AnimationSystem;
import com.shoebob.dotd.systems.LocationSystem;

public class AnimatedProjectile extends ProjectileEntity {
    public AnimationComponent animationComponent;

    public AnimatedProjectile(AnimationComponent animationComponent) {
        this.animationComponent = animationComponent;
        position = new PositionComponent();
    }

    @Override
    public void draw(DotD game) {
        float angle = velocity.vector.angleDeg();
        
        float constantSize = 16;
        
        float aspectRatio = (float) animationComponent.animation.getKeyFrames()[0].getRegionWidth() /
                            (float) animationComponent.animation.getKeyFrames()[0].getRegionHeight();
        
        float projectileWidth;
        float projectileHeight;
        if (aspectRatio >= 1) {
            projectileWidth = constantSize;
            projectileHeight = constantSize / aspectRatio;
        } else {
            projectileWidth = constantSize * aspectRatio;
            projectileHeight = constantSize;
        }
    
        // Draw the projectile with preserved aspect ratio
        game.batch.draw(AnimationSystem.getAnimationFrame(animationComponent, game),
                position.x,
                position.y,
                projectileWidth / 2,
                projectileHeight / 2,
                projectileWidth,
                projectileHeight,
                2,
                2,
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
