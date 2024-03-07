package com.shoebob.dotd.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.AttachableAnimation;

public class Consts {
    public static class Animations {
        public static final AttachableAnimation playerIdleAnimation = new AttachableAnimation(
                new Texture(Gdx.files.internal("player/player_idle.png")),
                0.5f,
                new Vector2[]{
                        new Vector2(2, 11),
                        new Vector2(2, 8.5f),
                },
                0
        );

        public static final AttachableAnimation playerWalkRAnimation = new AttachableAnimation(
                new Texture(Gdx.files.internal("player/player_walk_r.png")),
                0.25f,
                new Vector2[]{
                        new Vector2(3, 11),
                        new Vector2(3, 8.5f)
                },
                0
        );

        public static final AttachableAnimation playerWalkLAnimation = new AttachableAnimation(
                new Texture(Gdx.files.internal("player/player_walk_l.png")),
                0.25f,
                new Vector2[]{
                        new Vector2(6, 11),
                        new Vector2(6, 8.5f)
                },
                false,
                0
        );

        public static final AttachableAnimation playerWalkBAnimation = new AttachableAnimation(
                new Texture(Gdx.files.internal("player/player_walk_b.png")),
                0.25f,
                // just make it render behind the player - doesn't need to be completely visible
                new Vector2[]{
                        new Vector2(23, -1), // sort of jank, but whatever
                        new Vector2(23, -3.5f)
                },
                false,
                -90
        );

        // sheet for forward walk is just sped up idle
        public static final AttachableAnimation playerWalkFAnimation = new AttachableAnimation(
                new Texture(Gdx.files.internal("player/player_idle.png")),
                0.25f,
                new Vector2[]{
                        new Vector2(2, 11),
                        new Vector2(2, 8.5f)
                },
                0
        );
    }
}
