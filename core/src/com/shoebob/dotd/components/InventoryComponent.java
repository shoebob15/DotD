package com.shoebob.dotd.components;

import com.shoebob.dotd.entities.attachments.Attachment;
import com.shoebob.dotd.entities.attachments.MagicStaffAttachment;

import java.util.ArrayList;

public class InventoryComponent implements Component {
    public Attachment equipped = null;
    public ArrayList<Attachment> attachments = new ArrayList<>();
}
