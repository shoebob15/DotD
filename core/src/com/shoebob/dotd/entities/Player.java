package com.shoebob.dotd.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shoebob.dotd.AttachableAnimation;

public class Player extends AnimatedEntity { // TODO: Entity class

    private final Attachment magic_staff;

    public Player(float x, float y, AttachableAnimation idleAnimation, AttachableAnimation walkRAnimation, AttachableAnimation walkLAnimation, AttachableAnimation walkBAnimation, AttachableAnimation walkFAnimation) {
        super(x, y, idleAnimation, walkRAnimation, walkLAnimation, walkBAnimation, walkFAnimation);
        magic_staff = new Attachment(x, y, new Texture(Gdx.files.internal("weapons/magic_staff.png"))) {
            @Override
            public void use() {

            }

            @Override
            public void update() {

            }
        };
    }

    public void draw(SpriteBatch s) {
        if (getCurrentAnimation().shouldRenderOnTop()) {
            s.draw(getCurrentFrame(), x, y, width, height);
            magic_staff.draw(s, getCurrentAnimation().getRotation());
        } else {
            magic_staff.draw(s, getCurrentAnimation().getRotation());
            s.draw(getCurrentFrame(), x, y, width, height);
        }
    }

    public void update() {


        System.out.println(magic_staff);
    }

    public void dispose() {
        super.dispose();
    }

    @Override
    public String toString() {
        return "Player{" +
                "x=" + x +
                ", y=" + y +
                ", velocity=" + vector +
                '}';
    }
}
