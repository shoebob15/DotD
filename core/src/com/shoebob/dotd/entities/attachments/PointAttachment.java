package com.shoebob.dotd.entities.attachments;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.components.TextureComponent;
import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.spells.Spell;

// shoots point-type spells
public class PointAttachment extends Attachment {
    public Spell spell;

    public PointAttachment(Spell spell, TextureComponent texture) {
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
        if (!(game.player.mana.currentMana - game.player.attachmentInventory.selectedSpell.manaCost >= 0)) {
            return;
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
