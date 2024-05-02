package com.shoebob.dotd.entities.attachments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.components.BodyComponent;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.components.TextureComponent;
import com.shoebob.dotd.components.VelocityComponent;
import com.shoebob.dotd.entities.spells.SpellEntity;
import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.managers.ProjectileManager;
import com.shoebob.dotd.managers.SpellEntityManager;
import com.shoebob.dotd.spells.enums.SpellType;
import com.shoebob.dotd.systems.VelocitySystem;
import com.shoebob.dotd.util.Util;

// a weapon that will summon projectile-type spells
public class ProjectileAttachment extends Attachment {
    public ProjectileAttachment(TextureComponent texture) {
        this.texture = texture;
        this.position = new PositionComponent();
    }

    @Override
    public void draw(SpriteBatch s, float rotation) {
        super.draw(s, rotation);
    }

    @Override
    public void use(DotD game) {
        if (!(game.player.mana.currentMana - game.player.attachmentInventory.selectedSpell.manaCost >= 0)) {
            return;
        }

        if (cooldown()) {
            if (game.player.attachmentInventory.selectedSpell.type == SpellType.SPELL_PROJECTILE) {
                VelocityComponent vel = new VelocityComponent();

                // calculate projectile velocity based off mouse position
                vel.vector = new Vector2(Gdx.input.getX() - ((float) Gdx.graphics.getWidth() / 2),
                        (Gdx.input.getY() - ((float) Gdx.graphics.getHeight() / 2)) * -1);

                // normalize vector for uniform speed
                vel.vector.nor();

                // multiply projectile velocity by a factor of x
                vel.vector = VelocitySystem.mulVec(vel.vector, 2.5f);

                // put the velocity into the projectiles velocity component
                game.player.attachmentInventory.selectedSpell.projectile.velocity = vel;

                // set the projectiles position to the player
                PositionComponent tmp = new PositionComponent();
                tmp.x = game.player.position.x;
                tmp.y = game.player.position.y;
                game.player.attachmentInventory.selectedSpell.projectile.position = tmp;

                game.player.attachmentInventory.selectedSpell.projectile.animationComponent = game.player.attachmentInventory.selectedSpell.animation;

                ProjectileManager.addAnimatedProjectile(game.player.attachmentInventory.selectedSpell.projectile, 5f);

                game.player.mana.currentMana -= game.player.attachmentInventory.selectedSpell.manaCost;

                game.player.attachmentInventory.selectedSpell.projectile = game.player.attachmentInventory.selectedSpell.projectile.copy();
            } else if (game.player.attachmentInventory.selectedSpell.type == SpellType.SPELL_POINT) {
                SpellEntity entity = new SpellEntity((Util.screenToWorld(new PositionComponent(Gdx.input.getX() - game.player.attachmentInventory.selectedSpell.animation.animation.getKeyFrames()[0].getRegionWidth() / 2f, Gdx.input.getY()), game)), new BodyComponent(8, 8), game.player.attachmentInventory.selectedSpell);

                SpellEntityManager.addSpellEntity(entity);
            } else {
                throw new IllegalStateException("Unrecognized spell type. (program it)");
            }
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
