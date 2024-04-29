package com.shoebob.dotd.spells;

import com.badlogic.gdx.graphics.Color;

public enum SpellRarity {
    COMMON(Color.GRAY),
    UNCOMMON(Color.GREEN),
    RARE(Color.RED),
    LEGENDARY(Color.GOLD),
    MYTHIC(Color.MAROON);

    public final Color color;

    SpellRarity(Color color) {
        this.color = color;
    }
}
