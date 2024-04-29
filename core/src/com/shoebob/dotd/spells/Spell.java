package com.shoebob.dotd.spells;

import com.shoebob.dotd.entities.projectiles.AnimatedProjectile;

public class Spell {
    // name that shows in info panel
    public String name;

    // short and sweet description
    public String decription;

    // type of spell (ranged, area, etc.)
    public SpellType type;

    // effect of the spell if applicable
    public SpellEffect effect;

    // amount of mana the spell costs
    public int manaCost;

    // radius of area-type spells
    public int areaRadius;

    // damage the spell deals when it makes contact with an enemy
    public int damage;

    // how long the spell lasts for - only applies to area spells - projectiles simply have a lifespan in ProjectileMaanger
    public int duration;

    // who the spell is directed towards - player, enemy
    public TargetType target;

    // the current level on the spell - displayed as roman numeral in UI
    public int level = 1;

    public SpellRarity rarity;

    // the projectile shown for PROJECTILE-type spells
    public AnimatedProjectile projectile;

    public static class Builder {
        public final String name;

        public final String decription;

        public final SpellType type;

        public final SpellEffect effect;

        public final int manaCost;

        public final SpellRarity rarity;

        public int areaRadius;

        public int damage;

        public int duration;

        public final TargetType target;

        public int level = 1;

        public AnimatedProjectile projectile;

        public Builder(String name, String description, SpellType type, SpellEffect effect, TargetType target, int manaCost, SpellRarity rarity) {
            this.name = name;
            this.decription = description;
            this.type = type;
            this.effect = effect;
            this.target = target;
            this.manaCost = manaCost;
            this.rarity = rarity;
        }

        public Builder areaRadius(int areaRadius) {
            this.areaRadius = areaRadius;
            return this;
        }

        public Builder damage(int damage) {
            this.damage = damage;
            return this;
        }

        public Builder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder level(int level) {
            this.level = level;
            return this;
        }

        public Builder projectile(AnimatedProjectile projectile) {
            this.projectile = projectile;
            return this;
        }

        public Spell build() {
            Spell spell = new Spell();
            spell.name = name;
            spell.decription = decription;
            spell.type = type;
            spell.effect = effect;
            spell.manaCost = manaCost;
            spell.rarity = rarity;
            spell.areaRadius = areaRadius;
            spell.damage = damage;
            spell.duration = duration;
            spell.target = target;
            spell.level = level;
            spell.projectile = projectile;

            if (spell.type == SpellType.SPELL_PROJECTILE && spell.projectile == null) {
                throw new IllegalArgumentException("Projectile-type spells cannot be initialized without a projectile.");
            }
            return spell;
        }
    }

    @Override
    public String toString() {
        return "Spell{" +
                "name='" + name + '\'' +
                ", decription='" + decription + '\'' +
                ", type=" + type +
                ", effect=" + effect +
                ", manaCost=" + manaCost +
                ", areaRadius=" + areaRadius +
                ", damage=" + damage +
                ", duration=" + duration +
                ", target=" + target +
                ", level=" + level +
                '}';
    }
}
