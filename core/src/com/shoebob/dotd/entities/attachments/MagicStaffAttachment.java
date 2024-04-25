package com.shoebob.dotd.entities.attachments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.DotDGame;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.components.VelocityComponent;
import com.shoebob.dotd.entities.projectiles.FireballProjectile;
import com.shoebob.dotd.managers.ProjectileManager;
import com.shoebob.dotd.systems.VelocitySystem;
import com.shoebob.dotd.util.Consts;

// TODO: This code could be cleaned up a lot and this class could be deleted or turned into a different one
public class MagicStaffAttachment extends Attachment {
    @Override
    public void create() {
        super.create();

        texture.texture = new Texture("weapons/magic_staff.png");
    }

    @Override
    public void update() {
        Vector2 loc = DotDGame.player.animation.currentAnimation.getWorldAttachmentLocation(DotDGame.statetime,
                DotDGame.player
        );

        PositionComponent loc2 = new PositionComponent();
        loc2.x = loc.x;
        loc2.y = loc.y;

        position = loc2;
    }

    @Override
    public void draw(SpriteBatch s, float rotation) {
        super.draw(s, rotation);

        // draw projectile - TODO: Change fireball to spritesheet
        // TODO: Move all files to spritesheet for production - will require rewrite of spritesheet loading code
    }


    @Override
    public void use() {
//        if (cooldown()) {
            // TODO: Fireball shouldn't be its own class - possibly adapt builder model
            FireballProjectile proj = new FireballProjectile();
            VelocityComponent vel = new VelocityComponent();

            vel.vector = new Vector2(Gdx.input.getX() - ((float) Gdx.graphics.getWidth() / 2),
                    (Gdx.input.getY() - ((float) Gdx.graphics.getHeight() / 2)) * -1);

            vel.vector.nor();

            vel.vector = VelocitySystem.mulVec(vel.vector, .5f);

            proj.velocity = vel;

            PositionComponent pos = new PositionComponent();
            pos.x = DotDGame.player.position.x - 30;
            pos.y = DotDGame.player.position.y;
            proj.position = pos;

            proj.animationComponent = Consts.AnimationComponents.fireball;

            ProjectileManager.addAnimatedProjectile(proj, 5f);
//        }
    }
}
