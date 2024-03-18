package com.shoebob.dotd.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shoebob.dotd.DotDGame;
import com.shoebob.dotd.components.BodyComponent;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.components.SpriteAnimationComponent;
import com.shoebob.dotd.components.VelocityComponent;
import com.shoebob.dotd.entities.attachments.Attachment;

public class Player implements Entity {
    public PositionComponent position; // bad oop, but idk
    public BodyComponent body;
    public VelocityComponent velocity;
    public SpriteAnimationComponent animationComponent;
    private final Attachment magic_staff;


    @Override
    public void create() {
        position = new PositionComponent();
        body = new BodyComponent();
        body.width = 16;
        body.height = 16;
        velocity = new VelocityComponent();
        animationComponent = new SpriteAnimationComponent();
    }

    public void draw(SpriteBatch s) {
        if (getCurrentAnimation().shouldRenderOnTop()) {
            s.draw(getCurrentFrame(), position.x, position.y, body.width, body.height);
            magic_staff.draw(s, getCurrentAnimation().getRotation());
        } else {
            magic_staff.draw(s, getCurrentAnimation().getRotation());
            s.draw(getCurrentFrame(), position.x, position.y, body.width, body.height);
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

        vector.set(expX, expY);
        vector.nor();

        x += vector.x;
        y += vector.y;

        super.update(); // must be called after changing vector values
        magic_staff.setVectorLocation(getCurrentAnimation().getWorldAttachmentLocation(DotDGame.statetime, this));
        magic_staff.update();
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
