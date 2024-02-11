package com.shoebob.dotd;

import com.badlogic.gdx.Gdx;
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

    public Player() {
        idleSheet = new Texture(Gdx.files.internal("player_idle.png"));
        idleAnimation = new Animation<TextureRegion>(0.1f, getIdleFrames());
    }

    public void draw(SpriteBatch s) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion frame = idleAnimation.getKeyFrame(stateTime, true);
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
}
