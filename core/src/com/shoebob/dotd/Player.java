package com.shoebob.dotd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class Player { // TODO: Entity class
    // TODO: also better way of animating
    protected int x = 0, y = 0;
    private float stateTime = 0f;

    Animation<TextureRegion> idleAnimation;
    Texture idleSheet;

    Animation<TextureRegion> walkAnimation;
    Texture walkSheet;

    public Player() {
        idleSheet = new Texture(Gdx.files.internal("player_idle.png"));
        idleAnimation = new Animation<TextureRegion>(0.1f, getIdleFrames());
        walkSheet = new Texture(Gdx.files.internal("player_move.png"));
        walkAnimation = new Animation<TextureRegion>(0.1f, getWalkFrames());
    }

    public void draw(SpriteBatch s) {
        TextureRegion frame = idleAnimation.getKeyFrame(stateTime, true);
        // TODO: PLayer moves faster on diagonal. Something something normalize vector?
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                frame = walkAnimation.getKeyFrame(stateTime, true);
                y += 5;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                frame = walkAnimation.getKeyFrame(stateTime, true);
                y -= 5;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                frame = walkAnimation.getKeyFrame(stateTime, true);
                x -= 5;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                frame = walkAnimation.getKeyFrame(stateTime, true);
                x += 5;
            }
        } else {
            frame = idleAnimation.getKeyFrame(stateTime, true);
        }


        stateTime += Gdx.graphics.getDeltaTime();

        s.draw(frame, x, y, 128, 128);
    }

    public void dispose() {
        idleSheet.dispose();
    }

    private TextureRegion[] getIdleFrames() {
        TextureRegion[] tmp = new TextureRegion[4];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = new TextureRegion(idleSheet, i * 32, 0, 32, 32);
        }
        return tmp;
    }
    // TODO: Player shrinks while moving. Fix spritesheet file?
    private TextureRegion[] getWalkFrames() {
        TextureRegion[] tmp = new TextureRegion[6];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = new TextureRegion(walkSheet, i * 64, 0, 64, 64);
        }
        return tmp;
    }
}
