package com.shoebob.dotd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player { // TODO: Entity class
    // TODO: also better way of animating
    protected int x = 0, y = 0, width = 64, height = 64;
    private Vector2 velocity = new Vector2(0, 0);
    private float stateTime = 0f;

    Animation<TextureRegion> idleAnimation;
    Texture idleSheet;

    Animation<TextureRegion> walkAnimation;
    Texture walkSheet;

    public Player() {
        idleSheet = new Texture(Gdx.files.internal("player_idle.png"));
        idleAnimation = new Animation<TextureRegion>(0.5f, getIdleFrames());
        walkSheet = new Texture(Gdx.files.internal("player_walk_l.png"));
        walkAnimation = new Animation<TextureRegion>(0.125f, getWalkFrames());
    }

    public void draw(SpriteBatch s) {
        TextureRegion frame = idleAnimation.getKeyFrame(stateTime, true);
        // TODO: PLayer moves faster on diagonal. Something something normalize vector?
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                velocity.y = 1;
                frame = walkAnimation.getKeyFrame(stateTime, true);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                velocity.y = -1;
                frame = walkAnimation.getKeyFrame(stateTime, true);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                velocity.x = -1;
                frame = walkAnimation.getKeyFrame(stateTime, true);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                velocity.x = 1;
                frame = walkAnimation.getKeyFrame(stateTime, true);
            }
        } else {
            frame = idleAnimation.getKeyFrame(stateTime, true);
            velocity.setZero();
        }

        velocity.nor();
        x += velocity.x;
        y += velocity.y;


        stateTime += Gdx.graphics.getDeltaTime();

        s.draw(frame, x, y, width, height);
    }

    public void dispose() {
        idleSheet.dispose();
    }

    private TextureRegion[] getIdleFrames() {
        TextureRegion[] tmp = new TextureRegion[2];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = new TextureRegion(idleSheet, i * 16, 0, 16, 16);
        }
        return tmp;
    }

    private TextureRegion[] getWalkFrames() {
        TextureRegion[] tmp = new TextureRegion[2];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = new TextureRegion(walkSheet, i * 16, 0, 16, 16);
        }
        return tmp;
    }
}
