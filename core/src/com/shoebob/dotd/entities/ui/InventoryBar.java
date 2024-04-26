package com.shoebob.dotd.entities.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.shoebob.dotd.components.InventoryComponent;
import com.shoebob.dotd.entities.attachments.Attachment;
import com.shoebob.dotd.game.DotD;

// class for the bar at the bottom of the screen that has spells, weapons, etc.
public class InventoryBar extends OverlayEntity {
    InventoryComponent inventory;

    @Override
    public void create() {
        super.create();
        texture.texture = new Texture(Gdx.files.internal("ui/inventory.png"));
        screenPos.x = .05f;
        screenPos.y = .05f;

        inventory = new InventoryComponent();
    }


    // Precondition: inventory.size() <= 4 && >= 0
    public void loadInventory(InventoryComponent inventory) {
        this.inventory = inventory;
    }

    @Override
    public void update(DotD game) {
        super.update(game);
        float x = gamePos.x;
        float y = gamePos.y;

        int spacing = 17;
        int index = 0;
        // render current attachments in inventory
        for (Attachment attachment : inventory.attachments) {
            game.batch.draw(attachment.texture.texture, x + 33 + (spacing * index), y + 6,
                    attachment.texture.texture.getWidth() * .8f,
                    attachment.texture.texture.getHeight() * .8f
            );

            index++;
        }
    }
}
