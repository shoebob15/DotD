package com.shoebob.dotd.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.components.AnimationComponent;
import com.shoebob.dotd.entities.projectiles.AnimatedProjectile;
import com.shoebob.dotd.spells.Spell;
import com.shoebob.dotd.spells.SpellEffect;
import com.shoebob.dotd.spells.SpellType;
import com.shoebob.dotd.spells.TargetType;
import com.shoebob.dotd.systems.AnimationSystem;
import com.shoebob.dotd.util.AttachableAnimation;

public class Consts {
    // this is such bad java, but why can't it have #define???
    public static class AttachedAnimations {
        public static final AttachableAnimation playerIdleAnimation = new AttachableAnimation(
                new Texture(Gdx.files.internal("player/player_idle.png")),
                0.5f,
                new Vector2[]{
                        new Vector2(2, 11),
                        new Vector2(2, 8.5f),
                },
                0
        );

        public static final AttachableAnimation playerWalkRAnimation = new AttachableAnimation(
                new Texture(Gdx.files.internal("player/player_walk_r.png")),
                0.25f,
                new Vector2[]{
                        new Vector2(3, 11),
                        new Vector2(3, 8.5f)
                },
                0
        );

        public static final AttachableAnimation playerWalkLAnimation = new AttachableAnimation(
                new Texture(Gdx.files.internal("player/player_walk_l.png")),
                0.25f,
                new Vector2[]{
                        new Vector2(6, 11),
                        new Vector2(6, 8.5f)
                },
                false,
                0
        );

        public static final AttachableAnimation playerWalkBAnimation = new AttachableAnimation(
                new Texture(Gdx.files.internal("player/player_walk_b.png")),
                0.25f,
                // just make it render behind the player - doesn't need to be completely visible
                new Vector2[]{
                        new Vector2(23, -1), // sort of jank, but whatever
                        new Vector2(23, -3.5f)
                },
                false,
                -90
        );

        // sheet for forward walk is just sped up idle
        public static final AttachableAnimation playerWalkFAnimation = new AttachableAnimation(
                new Texture(Gdx.files.internal("player/player_idle.png")),
                0.25f,
                new Vector2[]{
                        new Vector2(2, 11),
                        new Vector2(2, 8.5f)
                },
                0
        );
    }

    public static class AnimationComponents {
        public static final AnimationComponent fireball =
                AnimationSystem.buildAnimationComponent("projectiles/fireball/", "fireball", 5, 0.1f);
    }

    public static class Projectiles {
        public static final AnimatedProjectile fireball = new AnimatedProjectile(AnimationComponents.fireball);
    }

    public static class Spells {
        public static final Spell fireball = new Spell.Builder("Fireball", "Kaboom!", SpellType.SPELL_PROJECTILE, SpellEffect.EFFECT_FIRE, TargetType.ENEMY, 10)
                .damage(10)
                .projectile(Projectiles.fireball) // TODO: should just be inline - no fireball class
                .build();
    }
}
