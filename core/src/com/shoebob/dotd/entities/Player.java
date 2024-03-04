package com.shoebob.dotd.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.AttachableAnimation;
import com.shoebob.dotd.Attachment;
import com.shoebob.dotd.MagicAttachment;

public class Player extends AnimatedEntity { // TODO: Entity class
    private Vector2 velocity = new Vector2(0, 0);
    private float stateTime = 0f; // TODO: Don't store statetime in player - why?


    private final Attachment magic_staff;

    public Player(float x, float y, AttachableAnimation idleAnimation, AttachableAnimation walkRAnimation, AttachableAnimation walkLAnimation, AttachableAnimation walkBAnimation, AttachableAnimation walkFAnimation) {
        super(x, y, idleAnimation, walkRAnimation, walkLAnimation, walkBAnimation, walkFAnimation);
    }

    public void draw(SpriteBatch s) {
        if (currentAnimation.shouldRenderOnTop()) {
            s.draw(currentFrame, x, y, width, height);
            magic_staff.draw(s, currentAnimation.getRotation());
        } else {
            magic_staff.draw(s, currentAnimation.getRotation());
            s.draw(currentFrame, x, y, width, height);
        }
    }

    public void update() {
        currentFrame = idleAnimation.getAnimation().getKeyFrame(stateTime, true);

        float expX = 0, expY = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
                expY += 1;
                currentAnimation = walkBAnimation;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                expY -= 1;
                currentAnimation = walkFAnimation;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                expX -= 1;
                currentAnimation = walkLAnimation;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                expX += 1;
                currentAnimation = walkRAnimation;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) { // temporary control - will probably change later
                magic_staff.use();
            }
        } else {
            currentAnimation = idleAnimation;
        }

        // if velocity is 0, then do idle animation
        // necessary if player is holding multiple keys at once
        if (velocity.isZero()) {
            currentAnimation = idleAnimation;
            magic_staff.setVectorLocation(idleAnimation.getWorldAttachmentLocation(stateTime, this));
        }

        currentFrame = currentAnimation.getAnimation().getKeyFrame(stateTime, true);
        magic_staff.setVectorLocation(currentAnimation.getWorldAttachmentLocation(stateTime, this));

        velocity.set(expX, expY);
        velocity.nor();

        x += velocity.x;
        y += velocity.y;


        stateTime += Gdx.graphics.getDeltaTime();

        System.out.println(magic_staff);
    }

    public void dispose() {
        idleAnimation.dispose();
        walkRAnimation.dispose();
        walkLAnimation.dispose();
        walkBAnimation.dispose();
        walkFAnimation.dispose();
    }

    @Override
    public String toString() {
        return "Player{" +
                "x=" + x +
                ", y=" + y +
                ", velocity=" + velocity +
                '}';
    }
}
