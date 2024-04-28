package com.shoebob.dotd.entities.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.shoebob.dotd.components.BodyComponent;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.components.SpellInventoryComponent;
import com.shoebob.dotd.entities.projectiles.AnimatedProjectile;
import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.systems.LocationSystem;

// vertical inventory bar that displays selectable spells
public class SpellInventoryBar extends OverlayEntity {
    SpellInventoryComponent inventory;

    @Override
    public void create() {
        super.create();

        texture.texture = new Texture("ui/vertical_inventory.png");
        screenPos.x = 0.05f;
        screenPos.y = 0.17f;

        isHidden = true; // hidden by default

        inventory = new SpellInventoryComponent();
    }

    public void loadInventory(SpellInventoryComponent inventory) {
        this.inventory = inventory;
    }

    @Override
    public void update(DotD game) {
        super.update(game);
        float x = gamePos.x;
        float y = gamePos.y;

        // check if clicked - if yes, then flip isHidden
        if (Gdx.input.justTouched()) {
            if (LocationSystem.pointOverlaps(new PositionComponent(Gdx.input.getX(), Gdx.input.getY()), new PositionComponent(35, 454), new BodyComponent(48, 48))) {
                isHidden = !isHidden;
            }
        }


        int spacing = 17;
        int index = 0;


        if (!isHidden) {
            // render current spells in inventory
            for (AnimatedProjectile spell : inventory.spells) {
                game.batch.draw(spell.animationComponent.animation.getKeyFrame(game.statetime), x - 3, y + 6 + (spacing * index),
                        spell.animationComponent.animation.getKeyFrames()[0].getRegionWidth() * .4f,
                        spell.animationComponent.animation.getKeyFrames()[0].getRegionHeight() * .4f
                );

                index++;
            }
        }
    }
}
