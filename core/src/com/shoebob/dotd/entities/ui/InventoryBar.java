package com.shoebob.dotd.entities.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
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


    // Precondition: inventory.size() <= 4 && >= 0 (why did I add this)
    public void loadInventory(InventoryComponent inventory) {
        this.inventory = inventory;
    }

    @Override
    public void update(DotD game) {
        super.update(game);
        float x = gamePos.x;
        float y = gamePos.y;

        int spacing = 17; // it just works! (completely meaningless number)
        int index = 0;

        // render currently selected spell
        game.batch.draw(inventory.selectedSpell.animationComponent.animation.getKeyFrame(game.statetime), x - 2, y + 6, 24, 12);


        // render current cooldown on spell

        int height = 0;

        if (inventory.equipped.lastAttack + inventory.equipped.cooldown > System.currentTimeMillis()) {
            height = (int) (19 - (19 * (System.currentTimeMillis() - inventory.equipped.lastAttack) / inventory.equipped.cooldown));
            System.out.println(height);
        }

        game.batch.draw(new Texture("ui/cooldown.png"), x + 3, y + 3, 19, height);


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
