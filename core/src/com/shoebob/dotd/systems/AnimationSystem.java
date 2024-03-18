package com.shoebob.dotd.systems;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.shoebob.dotd.DotDGame;
import com.shoebob.dotd.components.SpriteAnimationComponent;
import com.shoebob.dotd.components.VelocityComponent;

public class AnimationSystem {
    // returns sprite's animation frame based on the direction of the sprite and statetime
    public static TextureRegion getSpriteAnimationFrame(SpriteAnimationComponent animation, VelocityComponent velocity) {
        float direction = LocationSystem.getDirection(velocity);

        if (direction == 180) return animation.moveLAnimation.getAnimation().getKeyFrame(DotDGame.statetime);
        else if (direction == 0) return animation.moveRAnimation.getAnimation().getKeyFrame(DotDGame.statetime);
        else if (direction >= -135 && direction <= -45) return animation.moveFAnimation.getAnimation().getKeyFrame(DotDGame.statetime);
        else if (direction <= 135 && direction >= 45) return animation.moveBAnimation.getAnimation().getKeyFrame(DotDGame.statetime);

        if (velocity.vector.isZero()) return animation.idleAnimation.getAnimation().getKeyFrame(DotDGame.statetime);
        return animation.idleAnimation.getAnimation().getKeyFrame(DotDGame.statetime); // this shouldn't happen
    }

    public static void disposeSpriteAnimation(SpriteAnimationComponent animationComponent) {
        animationComponent.idleAnimation.dispose();
        animationComponent.moveLAnimation.dispose();
        animationComponent.moveRAnimation.dispose();
        animationComponent.moveBAnimation.dispose();
        animationComponent.moveFAnimation.dispose();
    }
}
