package com.shoebob.dotd.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureComponent implements Component {
    public Texture texture = new Texture(Gdx.files.internal("util/broken_texture.png"));
}
