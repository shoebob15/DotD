package com.shoebob.dotd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player { // TODO: Entity class
    protected float x = 0, y = 0, width = 32, height = 32;
    private Vector2 velocity = new Vector2(0, 0);
    private float stateTime = 0f;
    TextureRegion currentFrame;

    AttachableAnimation idleAnimation;
    Texture idleSheet = new Texture(Gdx.files.internal("player/player_idle.png"));

    Animation<TextureRegion> walkRAnimation;
    Texture walkRSheet;

    Animation<TextureRegion> walkLAnimation;
    Texture walkLSheet;

    Animation<TextureRegion> walkBAnimation;
    Texture walkBSheet;

    Animation<TextureRegion> walkFAnimation;
    // no texture sheet for walk forward, just sped up idle

    PlayerAttachment sword;

    public Player() {
        idleAnimation = new AttachableAnimation(
                new Texture(Gdx.files.internal("player/player_idle.png")),
                0.5f,
                new Vector2[] {
                    new Vector2(2, 9),
                    new Vector2(2, 11),
                });

        walkRSheet = new Texture(Gdx.files.internal("player/player_walk_r.png"));
        walkRAnimation = new Animation<TextureRegion>(0.125f, getFrames(walkRSheet, 2));

        walkLSheet = new Texture(Gdx.files.internal("player/player_walk_l.png"));
        walkLAnimation = new Animation<TextureRegion>(0.125f, getFrames(walkLSheet, 2));

        walkBSheet = new Texture(Gdx.files.internal("player/player_walk_b.png"));
        walkBAnimation = new Animation<TextureRegion>(0.125f, getFrames(walkBSheet, 2));

        // sheet for forward walk is just sped up idle
        walkFAnimation = new Animation<TextureRegion>(0.125f, getFrames(idleSheet, 2));

        sword = new PlayerAttachment(x, y, new Texture(Gdx.files.internal("weapons/sword.png")));
    }

    public void draw(SpriteBatch s) {
        s.draw(currentFrame, x, y, width, height);
        sword.draw(s);
        System.out.println(this);
    }

    public void update() {
        currentFrame = idleAnimation.animation.getKeyFrame(stateTime, true);

        float expX = 0, expY = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
                expY += 1;
                currentFrame = walkBAnimation.getKeyFrame(stateTime, true);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                expY -= 1;
                currentFrame = walkFAnimation.getKeyFrame(stateTime, true);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                expX -= 1;
                currentFrame = walkLAnimation.getKeyFrame(stateTime, true);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                expX += 1;
                currentFrame = walkRAnimation.getKeyFrame(stateTime, true);
            }
        } else {
            currentFrame = idleAnimation.animation.getKeyFrame(stateTime, true);
            System.out.println(idleAnimation.animation.getKeyFrameIndex(stateTime));
        }

        // if velocity is 0, then do idle animation
        if (velocity.isZero()) {
            currentFrame = idleAnimation.animation.getKeyFrame(stateTime, true);
            sword.setVectorLocation(idleAnimation.getWorldAttachmentLocation(stateTime, this));
        }


        velocity.set(expX, expY);
        velocity.nor();

        x += velocity.x;
        y += velocity.y;


        stateTime += Gdx.graphics.getDeltaTime();
    }

    public void dispose() {
        idleSheet.dispose();
        walkBSheet.dispose();
        walkLSheet.dispose();
        walkRSheet.dispose();
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
