package com.shoebob.dotd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

// provides coordinate data on where to put a player's attachment based on the current animation frame
public class AttachableAnimation {
    protected Animation<TextureRegion> animation;
    private Texture spritesheet;
    private Vector2[] coordinates;
    private Vector2 handPos = new Vector2(6.5f, 5.3f);


    public AttachableAnimation(Texture spritesheet, float update, Vector2[] coordinates) {
        this.spritesheet = spritesheet;
        this.animation = new Animation<>(
                update,
                new Array<TextureRegion>(getFrames(spritesheet.getWidth() / 16)),
                Animation.PlayMode.LOOP
        );
        this.coordinates = coordinates;
    }

    public Vector2 getLocalAttachmentLocation(float statetime) {
        return coordinates[animation.getKeyFrameIndex(statetime)];
    }

    public Vector2 getWorldAttachmentLocation(float statetime, Player player) {
        System.out.println(player);
        Vector2 tmp = getLocalAttachmentLocation(statetime);
        Vector2 tmp2 = new Vector2(tmp.x, tmp.y);

        return tmp2.add(new Vector2(player.x - handPos.x, player.y - handPos.y));
    }

    private TextureRegion[] getFrames(int length) {
        TextureRegion[] tmp = new TextureRegion[length];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = new TextureRegion(spritesheet, i * 16, 0, 16, 16);
        }
        return tmp;
    }

}
