package com.shoebob.dotd.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.components.AnimationComponent;
import com.shoebob.dotd.components.TextureComponent;
import com.shoebob.dotd.entities.enemies.EnemyEntity;
import com.shoebob.dotd.entities.projectiles.AnimatedProjectile;
import com.shoebob.dotd.spells.*;
import com.shoebob.dotd.spells.enums.SpellEffect;
import com.shoebob.dotd.spells.enums.SpellRarity;
import com.shoebob.dotd.spells.enums.SpellType;
import com.shoebob.dotd.spells.enums.TargetType;
import com.shoebob.dotd.systems.AnimationSystem;
import com.shoebob.dotd.components.AttachableAnimationComponent;

// a class that stores project-wide spells, animation, etc.

public class Consts {
    public static class AttachedAnimations {
        public static final AttachableAnimationComponent playerIdleAnimation = new AttachableAnimationComponent(
                new Texture(Gdx.files.internal("player/player_idle.png")),
                0.5f,
                new Vector2[]{
                        new Vector2(5, 1),
                        new Vector2(5, -1.5f),
                },
                0
        );

        public static final AttachableAnimationComponent playerWalkRAnimation = new AttachableAnimationComponent(
                new Texture(Gdx.files.internal("player/player_walk_r.png")),
                0.25f,
                new Vector2[]{
                        new Vector2(5, 0f),
                        new Vector2(5, -1.5f)
                },
                0
        );

        public static final AttachableAnimationComponent playerWalkLAnimation = new AttachableAnimationComponent(
                new Texture(Gdx.files.internal("player/player_walk_l.png")),
                0.25f,
                new Vector2[]{
                        new Vector2(5, 0),
                        new Vector2(5, -1.5f)
                },
                false,
                0
        );

        public static final AttachableAnimationComponent playerWalkBAnimation = new AttachableAnimationComponent(
                new Texture(Gdx.files.internal("player/player_walk_b.png")),
                0.25f,
                // just make it render behind the player - doesn't need to be completely visible
                new Vector2[]{
                        new Vector2(16, -3), // sort of jank, but whatever
                        new Vector2(16, -5.5f)
                },
                false,
                -90
        );

        // sheet for forward walk is just sped up idle
        public static final AttachableAnimationComponent playerWalkFAnimation = new AttachableAnimationComponent(
                new Texture(Gdx.files.internal("player/player_idle.png")),
                0.25f,
                new Vector2[]{
                        new Vector2(5, 1),
                        new Vector2(5, -1.5f),
                },
                0
        );
    }

    public static class AnimationComponents {
        public static final AnimationComponent fireball =
                AnimationSystem.buildHorizontalAnimationComponent(new TextureComponent(new Texture("projectiles/fireball.png")), 3, 0.1f);

        public static final AnimationComponent iceball =
                AnimationSystem.buildHorizontalAnimationComponent(new TextureComponent(new Texture("projectiles/iceball.png")), 3, 0.1f);

        public static final AnimationComponent lightning_strike =
                AnimationSystem.buildHorizontalAnimationComponent(new TextureComponent(new Texture("projectiles/lightning_strike.png")), 8, 0.1f);


        // zombie animation components
        public static final AnimationComponent zombieIdle =
                AnimationSystem.buildHorizontalAnimationComponent(new TextureComponent(new Texture("enemies/zombie/zombie_idle.png")), 2, 0.5f);

        public static final AnimationComponent zombieWalkR =
                AnimationSystem.buildHorizontalAnimationComponent(new TextureComponent(new Texture("enemies/zombie/zombie_walk_r.png")), 2, 0.25f);

        public static final AnimationComponent zombieWalkL =
                AnimationSystem.buildHorizontalAnimationComponent(new TextureComponent(new Texture("enemies/zombie/zombie_walk_l.png")), 2, 0.25f);

        public static final AnimationComponent zombieWalkB =
                AnimationSystem.buildHorizontalAnimationComponent(new TextureComponent(new Texture("enemies/zombie/zombie_walk_b.png")), 2, 0.25f);

        public static final AnimationComponent zombieWalkF =
                AnimationSystem.buildHorizontalAnimationComponent(new TextureComponent(new Texture("enemies/zombie/zombie_idle.png")), 2, 0.25f);
    }

    public static class Projectiles {
        public static final AnimatedProjectile fireball = new AnimatedProjectile(AnimationComponents.fireball);
        public static final AnimatedProjectile iceball = new AnimatedProjectile(AnimationComponents.iceball);
    }

    public static class Enemies {

    }

    public static class Spells {
        public static final Spell fireball = new Spell.Builder("Fireball", "Kaboom! Applies an fire effect when hit", SpellType.SPELL_PROJECTILE, SpellEffect.EFFECT_FIRE, TargetType.ENEMY, 10, SpellRarity.UNCOMMON, AnimationComponents.fireball)
                .damage(10)
                .level(5)
                .projectile(Projectiles.fireball)
                .build();

        public static final Spell iceball = new Spell.Builder("Iceball", "Chilly... Applies a ice effect when hit", SpellType.SPELL_PROJECTILE, SpellEffect.EFFECT_ICE, TargetType.ENEMY, 15, SpellRarity.RARE, AnimationComponents.iceball)
                .damage(10)
                .level(3)
                .projectile(Projectiles.iceball)
                .build();

        public static final Spell lightning_strike = new Spell.Builder("Lightning", "An electrifying zap", SpellType.SPELL_POINT, SpellEffect.EFFECT_NONE, TargetType.ENEMY, 20, SpellRarity.COMMON, AnimationComponents.lightning_strike)
                .damage(15)
                .level(3)
                .range(100)
                .build();
    }
}
