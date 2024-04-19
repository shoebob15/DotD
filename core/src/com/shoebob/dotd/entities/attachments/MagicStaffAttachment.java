package com.shoebob.dotd.entities.attachments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.DotDGame;
import com.shoebob.dotd.components.VelocityComponent;
import com.shoebob.dotd.entities.projectiles.FireballProjectile;
import com.shoebob.dotd.systems.AnimationSystem;
import com.shoebob.dotd.systems.LocationSystem;
import com.shoebob.dotd.systems.VelocitySystem;

public class MagicStaffAttachment extends Attachment {
    private FireballProjectile projectile;

    @Override
    public void create() {
        super.create();
        projectile = new FireballProjectile();
        projectile.create();
        texture.texture = new Texture("weapons/magic_staff.png");
    }


    @Override
    public void update() {
        projectile.update();
    }

    @Override
    public void draw(SpriteBatch s, float rotation) {
        super.draw(s, rotation);

        // draw projectile - TODO: Change fireball to spritesheet
        // TODO: Move all files to spritesheet for production - will require rewrite of spritesheet loading code

        projectile.draw(s);
    }


    @Override
    public void use() {
        VelocityComponent vel = new VelocityComponent();
        vel.vector = new Vector2(Gdx.input.getX() - ((float) Gdx.graphics.getWidth() / 2),
                (Gdx.input.getY() - ((float) Gdx.graphics.getHeight() / 2)) * -1);

        System.out.println(Gdx.input.getX() - ((float) Gdx.graphics.getWidth() / 2) + ", " +
                (Gdx.input.getY() - ((float) Gdx.graphics.getHeight() / 2)));

        vel.vector.nor();

        vel.vector = VelocitySystem.mulVec(3, vel.vector);

        projectile.velocity = vel;
        projectile.position = LocationSystem.clonePos(DotDGame.player.position);
    }
}
