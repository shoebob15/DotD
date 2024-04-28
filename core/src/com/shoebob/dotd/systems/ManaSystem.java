package com.shoebob.dotd.systems;

import com.shoebob.dotd.entities.Player;

public class ManaSystem {
    private static long lastManaUpdate;

    public static void updateMana(Player player) {
        if (lastManaUpdate == 0) {
            lastManaUpdate = System.currentTimeMillis();
        }

        if (player.mana.currentMana < player.mana.maxMana && System.currentTimeMillis() - lastManaUpdate > 500) {
            player.mana.currentMana += 1;
            lastManaUpdate = System.currentTimeMillis();
        }
    }
}
