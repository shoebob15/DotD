package com.shoebob.dotd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

// has coordinates for an attachment to render at for each frame.
public class AttachableAnimation {
    protected Animation<TextureRegion> animation;
    private Texture spritesheet;

    // represents the LOCAL coordinates of the spritesheet for the position of the players hand
    // !! MUST BE IN ORDER OF SPRITESHEET FRAMES !!

    protected Vector2[] coordinates;
    protected Vector2 currentVector;


    public AttachableAnimation(Texture spritesheet, float update, Vector2[] coordinates) {
        this.spritesheet = spritesheet;
        this.animation = new Animation<TextureRegion>(update, getFrames(spritesheet.getWidth() / 16));
        this.coordinates = coordinates;
    }

    private TextureRegion[] getFrames(int length) {
        TextureRegion[] tmp = new TextureRegion[length];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = new TextureRegion(spritesheet, i * 16, 0, 16, 16);
        }
        return tmp;
    }
}
