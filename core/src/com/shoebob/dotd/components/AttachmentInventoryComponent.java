package com.shoebob.dotd.components;

import com.shoebob.dotd.entities.attachments.Attachment;
import com.shoebob.dotd.entities.projectiles.AnimatedProjectile;

import java.util.ArrayList;

public class AttachmentInventoryComponent implements Component {
    public Attachment equipped = null;
    public ArrayList<Attachment> attachments = new ArrayList<>();

    public AnimatedProjectile selectedSpell = null;
}
