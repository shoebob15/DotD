package com.shoebob.dotd.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.shoebob.dotd.DotDGame;
import com.shoebob.dotd.components.AnimatedSpriteComponent;
import com.shoebob.dotd.components.AnimationComponent;
import com.shoebob.dotd.components.VelocityComponent;
import com.shoebob.dotd.util.AttachableAnimation;

public class AnimationSystem {
    // returns sprite's animation frame based on the direction of the sprite and statetime
    public static TextureRegion getSpriteAnimationFrame(AnimatedSpriteComponent animation, VelocityComponent velocity) {
        AttachableAnimation current = getSpriteAnimation(animation, velocity);
        return current.getAnimation().getKeyFrame(DotDGame.statetime);
    }

    // returns AttachableAnimation object based on direction
    public static AttachableAnimation getSpriteAnimation(AnimatedSpriteComponent animation, VelocityComponent velocity) {
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

    public static TextureRegion getAnimationFrame(AnimationComponent component) {
        return component.animation.getKeyFrame(DotDGame.statetime);
    }

    // naming system: name1, name2, name3
    // path is passed in as: path="weapons/name" - include the /
    // assumes that it is a png

    public static AnimationComponent buildAnimationComponent(String path, String name, int numFrames, float frameDuration) {
        AnimationComponent component = new AnimationComponent();
        component.animation = new Animation<TextureRegion>(frameDuration, new TextureRegion[numFrames]);

        for (int i = 0; i < component.animation.getKeyFrames().length; i++) {
            component.animation.getKeyFrames()[i] = new TextureRegion(
                    new Texture(Gdx.files.internal(path + name + (i + 1) + ".png"))
            );
        }
        return component;
    }


    public static void disposeSpriteAnimation(AnimatedSpriteComponent animationComponent) {
        animationComponent.idleAnimation.dispose();
        animationComponent.moveLAnimation.dispose();
        animationComponent.moveRAnimation.dispose();
        animationComponent.moveBAnimation.dispose();
        animationComponent.moveFAnimation.dispose();
    }
}
