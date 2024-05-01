package com.shoebob.dotd.spells.enums;

public enum SpellEffect {
    EFFECT_NONE("none"),
    EFFECT_FIRE("fire"),
    EFFECT_ICE("ice"),
    EFFECT_DARK("dark");

    public final String str;

    SpellEffect(String str) {
        this.str = str;
    }
}
