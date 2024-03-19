package com.shoebob.dotd.systems;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.shoebob.dotd.DotDGame;
import com.shoebob.dotd.components.SpriteAnimationComponent;
import com.shoebob.dotd.components.VelocityComponent;
import com.shoebob.dotd.util.AttachableAnimation;

public class AnimationSystem {
    // returns sprite's animation frame based on the direction of the sprite and statetime
    public static TextureRegion getSpriteAnimationFrame(SpriteAnimationComponent animation, VelocityComponent velocity) {
        AttachableAnimation current = getAnimation(animation, velocity);
        return current.getAnimation().getKeyFrame(DotDGame.statetime);
    }

    // returns AttachableAnimation object based on direction
    public static AttachableAnimation getAnimation(SpriteAnimationComponent animation, VelocityComponent velocity) {
        float direction = LocationSystem.getDirection(velocity);

        if (direction == 180) return animation.moveLAnimation;
        else if (direction == 0) return animation.moveRAnimation;
        else if (direction >= -135 && direction <= -45) return animation.moveFAnimation;
        else if (direction <= 135 && direction >= 45) return animation.moveBAnimation;
        else if (velocity.vector.isZero()) {
            return animation.idleAnimation;
        }
        else return animation.idleAnimation;
    }

    public static void disposeSpriteAnimation(SpriteAnimationComponent animationComponent) {
        animationComponent.idleAnimation.dispose();
        animationComponent.moveLAnimation.dispose();
        animationComponent.moveRAnimation.dispose();
        animationComponent.moveBAnimation.dispose();
        animationComponent.moveFAnimation.dispose();
    }
}
