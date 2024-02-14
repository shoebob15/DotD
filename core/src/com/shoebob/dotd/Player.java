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

    Animation<TextureRegion> idleAnimation;
    Texture idleSheet;

    Animation<TextureRegion> walkRAnimation;
    Texture walkRSheet;

    Animation<TextureRegion> walkLAnimation;
    Texture walkLSheet;

    Animation<TextureRegion> walkBAnimation;
    Texture walkBSheet;

    Animation<TextureRegion> walkFAnimation;

    public Player() {
        idleSheet = new Texture(Gdx.files.internal("player/player_idle.png"));
        idleAnimation = new Animation<TextureRegion>(0.5f, getFrames(idleSheet, 2));

        walkRSheet = new Texture(Gdx.files.internal("player/player_walk_r.png"));
        walkRAnimation = new Animation<TextureRegion>(0.125f, getFrames(walkRSheet, 2));

        walkLSheet = new Texture(Gdx.files.internal("player/player_walk_l.png"));
        walkLAnimation = new Animation<TextureRegion>(0.125f, getFrames(walkLSheet, 2));

        walkBSheet = new Texture(Gdx.files.internal("player/player_walk_b.png"));
        walkBAnimation = new Animation<TextureRegion>(0.125f, getFrames(walkBSheet, 2));

        // sheet for forward walk is just sped up idle
        walkFAnimation = new Animation<TextureRegion>(0.125f, getFrames(idleSheet, 2));
    }

    public void draw(SpriteBatch s) {
        TextureRegion frame = idleAnimation.getKeyFrame(stateTime, true);

        float expX = 0, expY = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
                expY += 1;
                frame = walkBAnimation.getKeyFrame(stateTime, true);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                expY += -1;
                frame = walkFAnimation.getKeyFrame(stateTime, true);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                expX += -1;
                frame = walkLAnimation.getKeyFrame(stateTime, true);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                expX += 1;
                frame = walkRAnimation.getKeyFrame(stateTime, true);
            }
        } else {
            frame = idleAnimation.getKeyFrame(stateTime, true);
        }

        // if velocity is 0, then do idle animation
        if (velocity.isZero()) {
            frame = idleAnimation.getKeyFrame(stateTime, true);
        }

        velocity.set(expX, expY);
        velocity.nor();

        x += velocity.x;
        y += velocity.y;


        stateTime += Gdx.graphics.getDeltaTime();

        s.draw(frame, x, y, width, height);
    }

    public void dispose() {
        idleSheet.dispose();
    }

    private TextureRegion[] getFrames(Texture sheet, int length) {
        TextureRegion[] tmp = new TextureRegion[length];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = new TextureRegion(sheet, i * 16, 0, 16, 16);
        }
        return tmp;
    }
}
