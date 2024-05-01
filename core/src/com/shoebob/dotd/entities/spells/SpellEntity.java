package com.shoebob.dotd.entities.spells;

import com.shoebob.dotd.components.BodyComponent;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.entities.Entity;
import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.spells.Spell;

// point and area-type spells
public class SpellEntity implements Entity {
    public PositionComponent position;
    public BodyComponent body;
    public Spell spell;

    public SpellEntity(PositionComponent position, BodyComponent body, Spell spell) {
        this.position = position;
        this.body = body;
        this.spell = spell;
    }

    @Override
    public void create() {
        // stupid method
    }

    @Override
    public void update(DotD game) {
        game.batch.draw(spell.animation.animation.getKeyFrame(game.statetime, false), position.x, position.y, body.width, body.height);
    }

    @Override
    public void dispose() {

    }
}
