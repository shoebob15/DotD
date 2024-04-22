package com.shoebob.dotd.entities.attachments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.DotDGame;
import com.shoebob.dotd.components.VelocityComponent;
import com.shoebob.dotd.entities.projectiles.FireballProjectile;
import com.shoebob.dotd.managers.ProjectileManager;
import com.shoebob.dotd.systems.AnimationSystem;
import com.shoebob.dotd.systems.LocationSystem;
import com.shoebob.dotd.systems.VelocitySystem;
import com.shoebob.dotd.util.Consts;

public class MagicStaffAttachment extends Attachment {
    @Override
    public void create() {
        super.create();

        texture.texture = new Texture("weapons/magic_staff.png");
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(SpriteBatch s, float rotation) {
        super.draw(s, rotation);

        // draw projectile - TODO: Change fireball to spritesheet
        // TODO: Move all files to spritesheet for production - will require rewrite of spritesheet loading code
    }


    @Override
    public void use() {
        if (cooldown()) {
            FireballProjectile proj = new FireballProjectile();
            VelocityComponent vel = new VelocityComponent();
            vel.vector = new Vector2(Gdx.input.getX() - ((float) Gdx.graphics.getWidth() / 2),
                    (Gdx.input.getY() - ((float) Gdx.graphics.getHeight() / 2)) * -1);

            vel.vector.nor();

            vel.vector = VelocitySystem.mulVec(vel.vector, 3);

            proj.velocity = vel;
            proj.position = LocationSystem.clonePos(DotDGame.player.position);
            proj.animationComponent = Consts.AnimationComponents.fireball;
            System.out.println("adding projectile");
            ProjectileManager.addAnimatedProjectile(proj, 5f);
        }
    }
}
