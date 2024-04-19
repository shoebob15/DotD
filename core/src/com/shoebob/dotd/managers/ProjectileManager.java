package com.shoebob.dotd.managers;

import com.shoebob.dotd.DotDGame;
import com.shoebob.dotd.entities.projectiles.AnimatedProjectile;
import com.shoebob.dotd.entities.projectiles.ProjectileEntity;

import java.util.ArrayList;

// class to manage projectiles
public class ProjectileManager {
    private static ArrayList<ProjectileEntity> projectiles;
    private static ArrayList<AnimatedProjectile> animatedProjectiles;

    public static void update() {
        for (ProjectileEntity projectile : projectiles) {
            projectile.update();
            projectile.draw(DotDGame.batch);
        }

        for (AnimatedProjectile projectile : animatedProjectiles) {
            projectile.update();
            projectile.draw(DotDGame.batch);
        }
    }

    public static void addProjectile(ProjectileEntity projectile, float lifespan) {
        projectiles.add(projectile);
    }

    public static void addAnimatedProjectile(AnimatedProjectile projectile, float lifespan) {
        animatedProjectiles.add(projectile);
    }

}
