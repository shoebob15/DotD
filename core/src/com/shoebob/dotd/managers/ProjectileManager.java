package com.shoebob.dotd.managers;

import com.shoebob.dotd.entities.projectiles.AnimatedProjectile;
import com.shoebob.dotd.entities.projectiles.ProjectileEntity;
import com.shoebob.dotd.game.DotD;

import java.util.ArrayList;

// class to manage projectiles
public class ProjectileManager {
    private static final ArrayList<ProjectileEntity> projectiles = new ArrayList<>();
    private static final ArrayList<AnimatedProjectile> animatedProjectiles = new ArrayList<>();

    public static void update(DotD game) {
        for (ProjectileEntity projectile : projectiles) {
            projectile.update(game);
            projectile.draw(game);
        }

        for (AnimatedProjectile projectile : animatedProjectiles) {
            projectile.update(game);
            projectile.draw(game);
        }
    }

    // TODO!!: This is so unoptimized - max of 1000 projectiles before lag (IMPLEMENT LIFESPAN THIS IS SO BAD)
    public static void addProjectile(ProjectileEntity projectile, float lifespan) {
        projectiles.add(projectile);
    }

    public static void addAnimatedProjectile(AnimatedProjectile projectile, float lifespan) {
        animatedProjectiles.add(projectile);
    }

    public static ArrayList<AnimatedProjectile> getAnimatedProjectiles() {
        return animatedProjectiles;
    }

}
