package com.shoebob.dotd.entities.spells;

import com.badlogic.gdx.Gdx;
import com.shoebob.dotd.components.AnimationComponent;
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
    public float localtime;

    public SpellEntity(PositionComponent position, BodyComponent body, Spell spell) {
        this.position = position;
        this.body = body;
        this.spell = spell;
        this.localtime = 0;
    }

    @Override
    public void create() {
        // stupid method - in the process of phasing out
    }

    @Override
    public void update(DotD game) {
        localtime += Gdx.graphics.getDeltaTime();
    }

    @Override
    public void dispose() {

    }
}
