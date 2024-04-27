package com.shoebob.dotd.components;

import com.shoebob.dotd.entities.attachments.Attachment;
import com.shoebob.dotd.entities.projectiles.AnimatedProjectile;
import com.shoebob.dotd.entities.projectiles.ProjectileEntity;

import java.util.ArrayList;

public class InventoryComponent implements Component {
    public Attachment equipped = null;
    public ArrayList<Attachment> attachments = new ArrayList<>();

    public AnimatedProjectile selectedSpell = null;
}
