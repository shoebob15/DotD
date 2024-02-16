package com.shoebob.dotd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

// provides coordinate data on where to put a player's attachment based on the current animation frame
public class AttachableAnimation {
    protected Animation<TextureRegion> animation;
    private Texture spritesheet;

    protected Vector2[] coordinates;


    public AttachableAnimation(Texture spritesheet, float update, Vector2[] coordinates) {
        this.spritesheet = spritesheet;
        this.animation = new Animation<>(update, getFrames(spritesheet.getWidth() / 16));
        this.coordinates = coordinates;

    }

    public Vector2 getLocalAttachmentLocation(float statetime) {
        return coordinates[animation.getKeyFrameIndex(statetime)];
    }

    private TextureRegion[] getFrames(int length) {
        TextureRegion[] tmp = new TextureRegion[length];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = new TextureRegion(spritesheet, i * 16, 0, 16, 16);
        }
        return tmp;
    }

}
