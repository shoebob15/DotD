package com.shoebob.dotd.entities.attachments;

import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.game.DotD;

// shoots point-type spells
public class PointAttachment extends Attachment {
    @Override
    public void use(DotD game) {
        // TODO: implement
        // SpellEntity + manager? gonna need work
    }

    @Override
    public void update(DotD game) {
        Vector2 loc = game.player.animation.currentAnimation.getWorldAttachmentLocation(game.statetime,
                game.player
        );

        position = new PositionComponent(loc.x, loc.y);
    }
}
