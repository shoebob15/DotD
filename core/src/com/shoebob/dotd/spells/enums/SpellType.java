package com.shoebob.dotd.spells.enums;

public enum SpellType {
    // area spells start at the position of the player and will move out based on the areaRadius
    SPELL_AREA,
    // simple projectile spell (fireball, etc.)
    SPELL_PROJECTILE,
    // spawns an attack at the mouse pointer (lightning strike) | limited by pointRange
    SPELL_POINT
}
