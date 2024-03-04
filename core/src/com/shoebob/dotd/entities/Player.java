package com.shoebob.dotd.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shoebob.dotd.AttachableAnimation;

public class Player extends AnimatedEntity {

    private final Attachment magic_staff;

    public Player(float x, float y, float width, float height, AttachableAnimation idleAnimation, AttachableAnimation walkRAnimation, AttachableAnimation walkLAnimation, AttachableAnimation walkBAnimation, AttachableAnimation walkFAnimation) {
        super(x, y, width, height, idleAnimation, walkRAnimation, walkLAnimation, walkBAnimation, walkFAnimation);
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
        magic_staff.draw(s, 0);
        s.draw(getCurrentFrame(), x, y, width, height);
    }

    public void update() {
        float expX = 0, expY = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
                expY += 1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                expY -= 1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                expX -= 1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                expX += 1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) { // TODO: temporary control - will probably change later
                magic_staff.use();
            }
        }

        vector.set(expX, expY);
        vector.nor();

        x += vector.x;
        y += vector.y;

        System.out.println(getDirection());
        super.update();
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
