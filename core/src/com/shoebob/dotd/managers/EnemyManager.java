package com.shoebob.dotd.managers;

import com.shoebob.dotd.components.BodyComponent;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.entities.enemies.EnemyEntity;
import com.shoebob.dotd.entities.projectiles.AnimatedProjectile;
import com.shoebob.dotd.entities.spells.SpellEntity;
import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.systems.LocationSystem;

import java.util.ArrayList;

public class EnemyManager {
    private static final ArrayList<EnemyEntity> enemies = new ArrayList<>();
    private static long lastSpawn = 0;

    public static void checkEnemies(DotD game) {
        for (int i = 0 ; i < enemies.size(); i++) { // no enhanced for because removing items
            for (SpellEntity entity : SpellEntityManager.getSpellEntities()) {
                if (LocationSystem.bodyOverlaps(entity.position, entity.body, enemies.get(i).position, enemies.get(i).body)) {
                    enemies.remove(i);
                    i--;
                }
            }

            for (AnimatedProjectile projectile : ProjectileManager.getAnimatedProjectiles()) {
                if (LocationSystem.bodyOverlaps(projectile.position, new BodyComponent(projectile.animationComponent.animation.getKeyFrames()[0].getRegionWidth(), projectile.animationComponent.animation.getKeyFrames()[0].getRegionHeight()), enemies.get(i).position, enemies.get(i).body)) {
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

        // spawn enemies
        if (enemies.size() <= 4 && System.currentTimeMillis() - lastSpawn > 3000) {
            lastSpawn = System.currentTimeMillis();
            PositionComponent tmp = new PositionComponent((int) (Math.random() * 250), (int) (Math.random() * 200));
            System.out.println(tmp);
            while (game.collisionObjectLayer.getCell((int) tmp.x / 8, (int) tmp.y / 8) != null) {
                tmp = new PositionComponent((int) (Math.random() * 150), (int) (Math.random() * 100));
            }
            System.out.println(tmp);
            addEnemy(new EnemyEntity(tmp));
        }
    }

    public static void addEnemy(EnemyEntity entity) {
        enemies.add(entity);
    }
}
