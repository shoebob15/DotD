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
        super.draw(s);
        // draw projectile - TODO: Change fireball to spritesheet
        s.draw(AnimationSystem.getAnimationFrame(projectile.animationComponent), projectile.position.x, projectile.position.y);
    }

    @Override
    public void use() {
            VelocityComponent vel = new VelocityComponent();
            vel.vector = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            vel.vector.nor();

            projectile.velocity = vel;
            projectile.position = LocationSystem.clonePos(DotDGame.player.position);
    }
}
