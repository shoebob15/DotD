package com.shoebob.dotd.managers;

import com.shoebob.dotd.DotDGame;
import com.shoebob.dotd.entities.projectiles.AnimatedProjectile;
import com.shoebob.dotd.entities.projectiles.ProjectileEntity;

import java.util.ArrayList;

// class to manage projectiles
public class ProjectileManager {
    private static final ArrayList<ProjectileEntity> projectiles = new ArrayList<>();
    private static final ArrayList<AnimatedProjectile> animatedProjectiles = new ArrayList<>();

    public static void update() {
        if (!projectiles.isEmpty()) {
            System.out.println("test");
            for (ProjectileEntity projectile : projectiles) {
                projectile.update();
                projectile.draw(DotDGame.batch);
            }
        }

        if (!animatedProjectiles.isEmpty()) {
            int count = 0;

            for (AnimatedProjectile projectile : animatedProjectiles) {
                projectile.update();
                projectile.draw(DotDGame.batch);
                System.out.println("Drawing projectile #" + count);
                count++;
            }
        }
    }

    public static void addProjectile(ProjectileEntity projectile, float lifespan) {
        projectiles.add(projectile);
    }

    public static void addAnimatedProjectile(AnimatedProjectile projectile, float lifespan) {
        animatedProjectiles.add(projectile);
        System.out.println("projectile added");
        System.out.println(animatedProjectiles.toString());
    }

}
