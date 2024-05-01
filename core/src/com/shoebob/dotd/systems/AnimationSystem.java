package com.shoebob.dotd.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.shoebob.dotd.components.AnimatedSpriteComponent;
import com.shoebob.dotd.components.AnimationComponent;
import com.shoebob.dotd.components.TextureComponent;
import com.shoebob.dotd.components.VelocityComponent;
import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.util.AttachableAnimationComponent;

public class AnimationSystem {
    // returns sprite's animation frame based on the direction of the sprite and statetime
    public static TextureRegion getSpriteAnimationFrame(AnimatedSpriteComponent animation, VelocityComponent velocity, DotD game) {
        AttachableAnimationComponent current = getSpriteAnimation(animation, velocity);
        return current.getAnimation().getKeyFrame(game.statetime);
    }

    // returns AttachableAnimationComponent object based on direction
    public static AttachableAnimationComponent getSpriteAnimation(AnimatedSpriteComponent animation, VelocityComponent velocity) {
        float direction = LocationSystem.getDirection(velocity);

        if (velocity.vector.isZero()) {
            return animation.idleAnimation;
        }

        if (direction == 180) return animation.moveLAnimation;
        else if (direction == 0) return animation.moveRAnimation;
        else if (direction >= -135 && direction <= -45) return animation.moveFAnimation;
        else if (direction <= 135 && direction >= 45) return animation.moveBAnimation;


        else return animation.idleAnimation;
    }

    public static TextureRegion getAnimationFrame(AnimationComponent component, DotD game) {
        return component.animation.getKeyFrame(game.statetime, true);
    }

    public static AnimationComponent buildHorizontalAnimationComponent(TextureComponent texture, int numFrames, float frameDuration) {
        TextureRegion[] tmp = new TextureRegion[numFrames];

        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = new TextureRegion(texture.texture, i * (texture.texture.getWidth() / numFrames), 0, texture.texture.getWidth() / numFrames, texture.texture.getHeight());
        }

        Animation<TextureRegion> animation = new Animation<>(frameDuration, tmp);
        return new AnimationComponent(animation);
    }

    public static AnimationComponent buildVerticalAnimationComponent(TextureComponent texture, int numFrames, float frameDuration) {
        TextureRegion[] tmp = new TextureRegion[numFrames];

        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = new TextureRegion(texture.texture, 0, i * texture.texture.getHeight() / numFrames, texture.texture.getWidth(), texture.texture.getHeight());
        }

        Animation<TextureRegion> animation = new Animation<>(frameDuration, tmp);
        return new AnimationComponent(animation);
    }


    public static void disposeSpriteAnimation(AnimatedSpriteComponent animationComponent) {
        animationComponent.idleAnimation.dispose();
        animationComponent.moveLAnimation.dispose();
        animationComponent.moveRAnimation.dispose();
        animationComponent.moveBAnimation.dispose();
        animationComponent.moveFAnimation.dispose();
    }
}
