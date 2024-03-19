package com.shoebob.dotd.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.shoebob.dotd.DotDGame;
import com.shoebob.dotd.components.BodyComponent;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.components.SpriteAnimationComponent;
import com.shoebob.dotd.components.VelocityComponent;
import com.shoebob.dotd.entities.attachments.Attachment;
import com.shoebob.dotd.systems.AnimationSystem;
import com.shoebob.dotd.systems.LocationSystem;
import com.shoebob.dotd.util.AttachableAnimation;

public class Player implements Entity {
    public PositionComponent position; // bad oop, but idk
    public BodyComponent body;
    public VelocityComponent velocity;
    public SpriteAnimationComponent animation;
    private Attachment magic_staff;


    @Override
    public void create() {
        position = new PositionComponent();
        body = new BodyComponent();
        body.width = 16;
        body.height = 16;
        velocity = new VelocityComponent();
        animation = new SpriteAnimationComponent();

        magic_staff = new Attachment() {
            @Override
            public void use() {

            }

            @Override
            public void update() {

            }

            @Override
            public void dispose() {
                // TODO : dispose in Attachment class
            }
        };
    }

    public void draw(SpriteBatch s) {
        AttachableAnimation current = AnimationSystem.getAnimation(animation, velocity);
        TextureRegion frame = current.getAnimation().getKeyFrame(DotDGame.statetime);

        if (current.shouldRenderOnTop()) {
            s.draw(frame, position.x, position.y, body.width, body.height);
            magic_staff.draw(s, current.getRotation());
        } else {
            magic_staff.draw(s, current.getRotation());
            s.draw(frame, position.x, position.y, body.width, body.height);
        }

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

        velocity.vector.set(expX, expY);
        velocity.vector.nor();

        LocationSystem.addVelocity(position, velocity);

        magic_staff.position = position;
        magic_staff.update();
    }

    public void dispose() {
        AnimationSystem.disposeSpriteAnimation(animation);
    }

    @Override
    public String toString() {
        return "Player{" +
                "x=" + position.x +
                ", y=" + position.y +
                ", velocity=" + velocity.vector +
                '}';
    }
}
