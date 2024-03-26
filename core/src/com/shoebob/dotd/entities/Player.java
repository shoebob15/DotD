package com.shoebob.dotd.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.DotDGame;
import com.shoebob.dotd.components.*;
import com.shoebob.dotd.entities.attachments.Attachment;
import com.shoebob.dotd.entities.attachments.MagicStaffAttachment;
import com.shoebob.dotd.systems.AnimationSystem;
import com.shoebob.dotd.systems.LocationSystem;

public class Player implements Entity {
    public PositionComponent position; // bad oop, but idk
    public BodyComponent body;
    public VelocityComponent velocity;
    public AnimatedSpriteComponent animation;
    private MagicStaffAttachment magic_staff;

    public Attachment currentAttachment; // for outside classes


    @Override
    public void create() {
        position = new PositionComponent();
        body = new BodyComponent();
        body.width = 32;
        body.height = 32;
        velocity = new VelocityComponent();
        animation = new AnimatedSpriteComponent();

        magic_staff = new MagicStaffAttachment();
        currentAttachment = magic_staff;
        magic_staff.create();
    }
    // TODO: Make rendering system - no stupid local calls
    public void draw(SpriteBatch s) {
        TextureRegion frame = animation.currentAnimation.getAnimation().getKeyFrame(DotDGame.statetime);

        if (animation.currentAnimation.shouldRenderOnTop()) {
            s.draw(frame, position.x, position.y, body.width, body.height);
            magic_staff.draw(s, animation.currentAnimation.getRotation());
        } else {
            magic_staff.draw(s, animation.currentAnimation.getRotation());
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

        animation.currentAnimation = AnimationSystem.getSpriteAnimation(animation, velocity);

        Vector2 loc = animation.currentAnimation.getWorldAttachmentLocation(DotDGame.statetime, this);
        PositionComponent loc2 = new PositionComponent();
        loc2.x = loc.x;
        loc2.y = loc.y;

        magic_staff.position = loc2;
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
