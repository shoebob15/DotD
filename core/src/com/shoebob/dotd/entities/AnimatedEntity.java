package com.shoebob.dotd.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.shoebob.dotd.AttachableAnimation;

public class AnimatedEntity extends Entity {
    private TextureRegion currentFrame;
    private AttachableAnimation currentAnimation;

    private final AttachableAnimation idleAnimation;

    private final AttachableAnimation walkRAnimation;

    private final AttachableAnimation walkLAnimation;

    private final AttachableAnimation walkBAnimation;

    private final AttachableAnimation walkFAnimation;

    public AnimatedEntity(float x, float y, AttachableAnimation idleAnimation,
                          AttachableAnimation walkRAnimation, AttachableAnimation walkLAnimation,
                          AttachableAnimation walkBAnimation, AttachableAnimation walkFAnimation) {
        this.idleAnimation = idleAnimation;
        this.walkRAnimation = walkRAnimation;
        this.walkLAnimation = walkLAnimation;
        this.walkBAnimation = walkBAnimation;
        this.walkFAnimation = walkFAnimation;
    }

    @Override
    public void update() {

    }
}
