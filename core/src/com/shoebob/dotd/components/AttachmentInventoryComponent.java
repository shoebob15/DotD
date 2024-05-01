package com.shoebob.dotd.components;

import com.shoebob.dotd.entities.attachments.Attachment;
import com.shoebob.dotd.entities.projectiles.AnimatedProjectile;
import com.shoebob.dotd.spells.Spell;

import java.util.ArrayList;

public class AttachmentInventoryComponent implements Component {
    public Attachment equipped = null;
    public ArrayList<Attachment> attachments = new ArrayList<>();

    public Spell selectedSpell = null;
}
