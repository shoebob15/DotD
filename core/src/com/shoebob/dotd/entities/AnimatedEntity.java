package com.shoebob.dotd.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.util.AttachableAnimation;
import com.shoebob.dotd.DotDGame;


// TODO: Change to animation component (somehow)
public class AnimatedEntity implements Entity {
    private TextureRegion currentFrame;
    private AttachableAnimation currentAnimation;

    private final AttachableAnimation idleAnimation;

    private final AttachableAnimation walkRAnimation;

    private final AttachableAnimation walkLAnimation;

    private final AttachableAnimation walkBAnimation;

    private final AttachableAnimation walkFAnimation;

    public AnimatedEntity(float x, float y, float width, float height, AttachableAnimation idleAnimation,
                          AttachableAnimation walkRAnimation, AttachableAnimation walkLAnimation,
                          AttachableAnimation walkBAnimation, AttachableAnimation walkFAnimation) {
        super(x, y, width, height, new Vector2(), null);
        this.idleAnimation = idleAnimation;
        this.walkRAnimation = walkRAnimation;
        this.walkLAnimation = walkLAnimation;
        this.walkBAnimation = walkBAnimation;
        this.walkFAnimation = walkFAnimation;
    }

    // super.update should always be called
    @Override
    public void update() {
        double direction = getDirection();

        if (direction == 180) currentAnimation = walkLAnimation;
        else if (direction == 0) currentAnimation = walkRAnimation;
        else if (direction >= -135 && direction <= -45) currentAnimation = walkFAnimation;
        else if (direction <= 135 && direction >= 45) currentAnimation = walkBAnimation;

        if (vector.isZero()) currentAnimation = idleAnimation;

        currentFrame = currentAnimation.getAnimation().getKeyFrame(DotDGame.statetime);
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
