package com.shoebob.dotd.managers;

import com.shoebob.dotd.entities.spells.SpellEntity;
import com.shoebob.dotd.game.DotD;

import java.util.ArrayList;

public class SpellEntityManager {
    private static final ArrayList<SpellEntity> spellEntities = new ArrayList<>();

    public static void update(DotD game) {
//        for (SpellEntity entity : spellEntities) {
//            if (entity.spell.animation.animation.getKeyFrameIndex(game.statetime) == entity.spell.animation.animation.getKeyFrames().length - 1) {
//                entity.dispose();
//                spellEntities.remove(entity);
//            }
//        }

        for (SpellEntity entity : spellEntities) {
            game.batch.draw(entity.spell.animation.animation.getKeyFrame(game.statetime, true), entity.position.x, entity.position.y);
        }
    }

    public static void addSpellEntity(SpellEntity entity) {
        spellEntities.add(entity);
    }
}
