package com.shoebob.dotd.managers;

import com.shoebob.dotd.entities.enemies.EnemyEntity;
import com.shoebob.dotd.entities.spells.SpellEntity;
import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.systems.LocationSystem;

import java.util.ArrayList;

public class EnemyManager {
    private static final ArrayList<EnemyEntity> enemies = new ArrayList<>();

    public static void checkEnemies(DotD game) {
        for (int i = 0 ; i < enemies.size(); i++) {
            for (SpellEntity entity : SpellEntityManager.getSpellEntities()) {
                if (LocationSystem.bodyOverlaps(entity.position, entity.body, enemies.get(i).position, enemies.get(i).body)) {
                    enemies.remove(i);
                    i--;
                }
            }
        }
    }

    public static void update(DotD game) {
        checkEnemies(game);
        for (EnemyEntity enemy : enemies) {
            enemy.update(game);
        }
    }

    public static void addEnemy(EnemyEntity entity) {
        enemies.add(entity);
    }
}
