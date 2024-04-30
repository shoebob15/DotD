package com.shoebob.dotd.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationComponent implements Component {
    public Animation<TextureRegion> animation = null;

    public AnimationComponent() {}
    public AnimationComponent(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

}
