package com.shoebob.dotd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player { // TODO: Entity class
    protected float x = 0, y = 0, width = 32, height = 32;
    private Vector2 velocity = new Vector2(0, 0);
    private float stateTime = 0f;

    private TextureRegion currentFrame;
    private AttachableAnimation currentAnimation;

    private final AttachableAnimation idleAnimation;
    private final Texture idleSheet;

    private final AttachableAnimation walkRAnimation;

    private final AttachableAnimation walkLAnimation;

    private final AttachableAnimation walkBAnimation;

    private final AttachableAnimation walkFAnimation;
    // no texture sheet for walk forward, just sped up idle

    private final PlayerAttachment sword;

    public Player() {
        idleAnimation = new AttachableAnimation(
                new Texture(Gdx.files.internal("player/player_idle.png")),
                0.5f,
                new Vector2[] {
                    new Vector2(2, 11),
                    new Vector2(2, 9),
                },
                0
        );
        idleSheet = new Texture(Gdx.files.internal("player/player_idle.png"));

        walkRAnimation = new AttachableAnimation(
                new Texture(Gdx.files.internal("player/player_walk_r.png")),
                0.25f,
                new Vector2[] {
                        new Vector2(3, 11),
                        new Vector2(3, 10)
                },
                0
        );

        walkLAnimation = new AttachableAnimation(
                new Texture(Gdx.files.internal("player/player_walk_l.png")),
                0.25f,
                new Vector2[]{
                        new Vector2(6, 11),
                        new Vector2(6, 10)
                },
                false,
                0
        );

        walkBAnimation = new AttachableAnimation(
                new Texture(Gdx.files.internal("player/player_walk_b.png")),
                0.25f,
                // just make it render behind the player - doesn't need to be visible
                new Vector2[]{
                        new Vector2(23, 3), // sort of jank, but whatever
                        new Vector2(23, 2)
                },
                false,
                -90
        );

        // sheet for forward walk is just sped up idle
        walkFAnimation = new AttachableAnimation(
                new Texture(Gdx.files.internal("player/player_idle.png")),
                0.25f,
                new Vector2[]{
                        new Vector2(1, 11),
                        new Vector2(1, 9)
                },
                0
        );

        sword = new PlayerAttachment(x, y, new Texture(Gdx.files.internal("weapons/sword.png")));
    }

    public void draw(SpriteBatch s) {
        if (currentAnimation.shouldRenderOnTop()) {
            s.draw(currentFrame, x, y, width, height);
            sword.draw(s, currentAnimation.getRotation());
        } else {
            sword.draw(s, currentAnimation.getRotation());
            s.draw(currentFrame, x, y, width, height);
        }
    }

    public void update() {
        currentFrame = idleAnimation.animation.getKeyFrame(stateTime, true);

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
        } else {
            currentAnimation = idleAnimation;
        }

        // if velocity is 0, then do idle animation
        if (velocity.isZero()) {
            currentAnimation = idleAnimation;
            sword.setVectorLocation(idleAnimation.getWorldAttachmentLocation(stateTime, this));
        }

        currentFrame = currentAnimation.animation.getKeyFrame(stateTime, true);
        sword.setVectorLocation(currentAnimation.getWorldAttachmentLocation(stateTime, this));

        velocity.set(expX, expY);
        velocity.nor();

        x += velocity.x;
        y += velocity.y;


        stateTime += Gdx.graphics.getDeltaTime();
    }

    public void dispose() {
        idleAnimation.dispose();
        walkRAnimation.dispose();
        walkLAnimation.dispose();
        walkBAnimation.dispose();
        walkFAnimation.dispose();
    }


    public AttachableAnimation getCurrentAnimation() {
        return currentAnimation;
    }

    private TextureRegion[] getFrames(Texture texture, int length) {
        TextureRegion[] tmp = new TextureRegion[length];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = new TextureRegion(texture, i * 16, 0, 16, 16);
        }
        return tmp;
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
