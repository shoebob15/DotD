package com.shoebob.dotd.spells.enums;

import com.badlogic.gdx.graphics.Color;

public enum SpellRarity {
    COMMON(Color.GRAY, "common"),
    UNCOMMON(Color.GREEN, "uncommon"),
    RARE(Color.RED, "rare"),
    LEGENDARY(Color.GOLD, "legendary"),
    MYTHIC(Color.MAROON, "mythic");

    public final Color color;
    public final String str;

    SpellRarity(Color color, String str) {
        this.color = color;
        this.str = str;
    }
}
