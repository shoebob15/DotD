package com.shoebob.dotd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

// used for wands, staffs, etc.
public class MagicAttachment extends Attachment {
    public MagicAttachment(float x, float y, Texture texture) {
        super(x, y, texture);
    }

    public void use() {
        System.out.println(Gdx.input.getX());
    }
}
