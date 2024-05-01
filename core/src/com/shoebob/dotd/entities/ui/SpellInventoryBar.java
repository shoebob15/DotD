package com.shoebob.dotd.entities.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.shoebob.dotd.components.BodyComponent;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.components.SpellInventoryComponent;
import com.shoebob.dotd.components.TextureComponent;
import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.spells.Spell;
import com.shoebob.dotd.systems.LocationSystem;
import com.shoebob.dotd.util.Util;

// vertical inventory bar that displays selectable spells
public class SpellInventoryBar extends OverlayEntity {
    SpellInventoryComponent inventory;

    TextureComponent dialogTexture;

    @Override
    public void create() {
        super.create();
        dialogTexture = new TextureComponent();
        texture.texture = new Texture("ui/vertical_inventory.png");
        dialogTexture.texture = new Texture("ui/item_info_dialog.png");
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
            for (Spell spell : inventory.spells) {
                game.batch.draw(spell.projectile.animationComponent.animation.getKeyFrame(game.statetime, true), x - 3, y + 6 + (spacing * index),
                        spell.projectile.animationComponent.animation.getKeyFrames()[0].getRegionWidth() * .4f,
                        spell.projectile.animationComponent.animation.getKeyFrames()[0].getRegionHeight() * .4f
                );

                index++;
            }

            checkInfoDialog(game);
        }
    }

    // will, if the mouse is hovered over any of the spells, display an info dialog about the spell
    private void checkInfoDialog(DotD game) {
        for (int i = 0; i < inventory.spells.size(); i++) {
            // yeah, I'm never touching this code again
            if (LocationSystem.pointOverlaps((Util.screenToWorld(new PositionComponent(Gdx.input.getX(), Gdx.input.getY()), game)), new PositionComponent(gamePos.x, gamePos.y + texture.texture.getHeight()), new BodyComponent(texture.texture.getWidth(), texture.texture.getHeight()))) {
                if (Util.screenToWorld(new PositionComponent(Gdx.input.getX(), Gdx.input.getY()), game).y > i * (texture.texture.getHeight() / 4f) + gamePos.y && Util.screenToWorld(new PositionComponent(Gdx.input.getX(), Gdx.input.getY()), game).y < (i * texture.texture.getHeight() / 4f + texture.texture.getHeight() / 4f) + gamePos.y) {
                    // get currently selected spell
                    Spell currentSpell = inventory.spells.get(i);
                    // draw dialog window
                    game.batch.draw(dialogTexture.texture, Util.screenToWorld(new PositionComponent(Gdx.input.getX(), Gdx.input.getY()), game).x + 10, Util.screenToWorld(new PositionComponent(Gdx.input.getX(), Gdx.input.getY()), game).y + 10, 140, 70);

                    // set scale to fit
                    game.font.getData().setScale(.3f);

                    game.font.setColor(Color.WHITE);

                    // draw name of spell
                    game.font.draw(game.batch, currentSpell.name + " - Level " + Util.getRomanNumber(currentSpell.level), Util.screenToWorld(new PositionComponent(Gdx.input.getX(), Gdx.input.getY()), game).x + 18, Util.screenToWorld(new PositionComponent(Gdx.input.getX(), Gdx.input.getY()), game).y + 70);

                    // set color to rarity color
                    game.font.setColor(currentSpell.rarity.color);

                    // draw rarity level
                    game.font.draw(game.batch, currentSpell.rarity.str.toUpperCase(), Util.screenToWorld(new PositionComponent(Gdx.input.getX(), Gdx.input.getY()), game).x + 18, Util.screenToWorld(new PositionComponent(Gdx.input.getX(), Gdx.input.getY()), game).y + 60);

                    game.font.setColor(Color.WHITE);

                    // draw damage level
                    game.font.draw(game.batch, "Damage: " + currentSpell.damage, Util.screenToWorld(new PositionComponent(Gdx.input.getX(), Gdx.input.getY()), game).x + 18, Util.screenToWorld(new PositionComponent(Gdx.input.getX(), Gdx.input.getY()), game).y + 50);

                    // draw mana level
                    game.font.draw(game.batch, "Mana: " + currentSpell.manaCost, Util.screenToWorld(new PositionComponent(Gdx.input.getX(), Gdx.input.getY()), game).x + 18, Util.screenToWorld(new PositionComponent(Gdx.input.getX(), Gdx.input.getY()), game).y + 40);

                    // draw effect
                    game.font.draw(game.batch, "Effect: " + currentSpell.effect.str.toUpperCase(), Util.screenToWorld(new PositionComponent(Gdx.input.getX(), Gdx.input.getY()), game).x + 18, Util.screenToWorld(new PositionComponent(Gdx.input.getX(), Gdx.input.getY()), game).y + 30);
                }
            }
        }
    }
}
