package com.shoebob.dotd.entities.attachments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
import com.shoebob.dotd.spells.Spell;
import com.shoebob.dotd.spells.enums.SpellType;
import com.shoebob.dotd.systems.VelocitySystem;
import com.shoebob.dotd.util.Util;

// a weapon that will summon projectile-type spells
public class SpellAttachment extends Attachment {
    private Texture pointRadiusTexture;

    public SpellAttachment(TextureComponent texture) {
        this.texture = texture;
        this.position = new PositionComponent();
        this.pointRadiusTexture = new Texture("projectiles/range_circle.png");
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
            // PROJECTILE ATTACHMENTS
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

                game.player.attachmentInventory.selectedSpell.projectile = game.player.attachmentInventory.selectedSpell.projectile.copy();
            }
            // POINT_TYPE SPELLS
            else if (game.player.attachmentInventory.selectedSpell.type == SpellType.SPELL_POINT) {
                if (inRange(game)) {
                    SpellEntity entity = new SpellEntity((Util.screenToWorld(new PositionComponent(Gdx.input.getX() - game.player.attachmentInventory.selectedSpell.animation.animation.getKeyFrames()[0].getRegionWidth() / 2f, Gdx.input.getY()), game)), new BodyComponent(8, 8), game.player.attachmentInventory.selectedSpell);

                    SpellEntityManager.addSpellEntity(entity);
                } else {
                    lastAttack -= cooldown;
                    game.player.mana.currentMana += game.player.attachmentInventory.selectedSpell.manaCost;
                }
            } else {
                throw new IllegalStateException("Unrecognized spell type. (program it)");
            }

            game.player.mana.currentMana -= game.player.attachmentInventory.selectedSpell.manaCost;
        }
    }

    @Override
    public void update(DotD game) {
        Vector2 loc = game.player.animation.currentAnimation.getWorldAttachmentLocation(game.statetime,
                game.player
        );
        if (game.player.attachmentInventory.selectedSpell.type == SpellType.SPELL_POINT) {
            drawRangeCircle(game.player.attachmentInventory.selectedSpell, game);
        }

        position = new PositionComponent(loc.x, loc.y);
    }

    // draws the range circle for point-type spells
    private void drawRangeCircle(Spell spell, DotD game) {
        if (spell.type != SpellType.SPELL_POINT) {
            throw new IllegalArgumentException("Spell must be a SPELL_POINT type");
        }

        // cleanest line of code ever
        game.batch.draw(pointRadiusTexture, game.player.position.x - spell.range / 2f + game.player.animation.currentAnimation.getAnimation().getKeyFrames()[0].getRegionWidth(), game.player.position.y - spell.range / 2f, spell.range, spell.range + game.player.animation.currentAnimation.getAnimation().getKeyFrames()[0].getRegionHeight() / 2f);
    }

    // returns if the pointers current location is withing range of the range circle
    private boolean inRange(DotD game) {
        int screenMouseX = Gdx.input.getX();
        int screenMouseY = Gdx.input.getY();

        int mouseX = (int) Util.screenToWorld(screenMouseX, screenMouseY, game).x;
        int mouseY = (int) Util.screenToWorld(screenMouseX, screenMouseY, game).y;

        // center of the player
        int playerX = (int) (game.player.position.x + (game.player.body.width / 2f));
        int playerY = (int) (game.player.position.y + (game.player.body.height / 2f));

        int hypotenuse = (int) Math.sqrt(((int) Math.pow((mouseX - playerX), 2) + Math.pow((mouseY - playerY), 2)));

        return hypotenuse < game.player.attachmentInventory.selectedSpell.range / 2;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
