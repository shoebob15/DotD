package com.shoebob.dotd.managers;

import com.shoebob.dotd.entities.spells.SpellEntity;
import com.shoebob.dotd.game.DotD;

import java.util.ArrayList;

public class SpellEntityManager {
    private static final ArrayList<SpellEntity> spellEntities = new ArrayList<>();

    public static void update(DotD game) {
        for (int i = 0; i < spellEntities.size(); i++) {
            spellEntities.get(i).update(game);
            if (spellEntities.get(i).spell.animation.animation.getKeyFrameIndex(spellEntities.get(i).localtime) == spellEntities.get(i).spell.animation.animation.getKeyFrames().length - 1) {
                spellEntities.remove(i);
                i--;
            }
        }

        for (SpellEntity entity : spellEntities) {
            game.batch.draw(entity.spell.animation.animation.getKeyFrame(entity.localtime, true), entity.position.x, entity.position.y);
        }
    }

    public static void addSpellEntity(SpellEntity entity) {
        spellEntities.add(entity);
    }

    public static ArrayList<SpellEntity> getSpellEntities() {
        return spellEntities;
    }
}
