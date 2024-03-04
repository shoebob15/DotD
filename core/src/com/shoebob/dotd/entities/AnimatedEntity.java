package com.shoebob.dotd.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    // super.update should always be called
    @Override
    public void update() {
        System.out.println(getDirection());
    }

    @Override
    public void draw(SpriteBatch s) {
        s.draw(currentFrame, x, y, width, height);
    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public AttachableAnimation getCurrentAnimation() {
        return currentAnimation;
    }

    @Override
    public void dispose() {
        currentAnimation.dispose();
        idleAnimation.dispose();
        walkRAnimation.dispose();
        walkLAnimation.dispose();
        walkBAnimation.dispose();
        walkFAnimation.dispose();
    }
}
