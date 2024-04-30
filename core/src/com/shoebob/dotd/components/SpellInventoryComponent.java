package com.shoebob.dotd.components;

import com.shoebob.dotd.entities.projectiles.AnimatedProjectile;
import com.shoebob.dotd.spells.Spell;

import java.util.ArrayList;

public class SpellInventoryComponent implements Component {
    public ArrayList<Spell> spells = new ArrayList<>();
}
