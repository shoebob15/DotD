package com.shoebob.dotd.entities.attachments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.components.TextureComponent;
import com.shoebob.dotd.components.VelocityComponent;
import com.shoebob.dotd.entities.projectiles.FireballProjectile;
import com.shoebob.dotd.game.Consts;
import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.managers.ProjectileManager;
import com.shoebob.dotd.spells.Spell;
import com.shoebob.dotd.systems.VelocitySystem;

// a weapon that will summon projectile-type spells
public class ProjectileAttachment extends Attachment {
    public Spell spell;

    public ProjectileAttachment(Spell spell, TextureComponent texture) {
        this.texture = texture;
        this.position = new PositionComponent();
        this.spell = spell;
    }

    @Override
    public void draw(SpriteBatch s, float rotation) {
        super.draw(s, rotation);
    }

    @Override
    public void use(DotD game) {
        if (!(game.player.mana.currentMana - 10 >= 0)) {
            return;
        }

        if (cooldown()) {
            VelocityComponent vel = new VelocityComponent();
            PositionComponent pos = new PositionComponent();

            // calculate projectile velocity based off mouse position
            vel.vector = new Vector2(Gdx.input.getX() - ((float) Gdx.graphics.getWidth() / 2),
                    (Gdx.input.getY() - ((float) Gdx.graphics.getHeight() / 2)) * -1);

            // normalize vector for uniform speed
            vel.vector.nor();

            // multiply projectile velocity by a factor of x
            vel.vector = VelocitySystem.mulVec(vel.vector, 2.5f);

            // put the velocity into the projectiles velocity component
            spell.projectile.velocity = vel;

            // set the projectiles position to the player
            pos.x = game.player.position.x - 30;
            pos.y = game.player.position.y;
            spell.projectile.position = pos;

            spell.projectile.animationComponent = Consts.AnimationComponents.fireball;

            ProjectileManager.addAnimatedProjectile(spell.projectile, 5f);

            game.player.mana.currentMana -= 10;

            spell.projectile = new FireballProjectile(); // TODO: this wont work
        }
    }

    @Override
    public void update(DotD game) {
        Vector2 loc = game.player.animation.currentAnimation.getWorldAttachmentLocation(game.statetime,
                game.player
        );

        position = new PositionComponent(loc.x, loc.y);
    }
}