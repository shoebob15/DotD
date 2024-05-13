package com.shoebob.dotd.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.shoebob.dotd.components.*;
import com.shoebob.dotd.entities.attachments.SpellAttachment;
import com.shoebob.dotd.game.Consts;
import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.systems.AnimationSystem;
import com.shoebob.dotd.systems.LocationSystem;

public class Player implements Entity {
    public PositionComponent position; // bad oop, but idc
    public BodyComponent body;
    public VelocityComponent velocity;
    public AttachedAnimatedSpriteComponent animation;
    public AttachmentInventoryComponent attachmentInventory;
    public SpellInventoryComponent spellInventory;
    public HealthComponent health;
    public ManaComponent mana;
    private SpellAttachment magic_staff; // TODO; should be in cosnts

    // if there is a collision tile
    private boolean tileLeft = false;
    private boolean tileRight = false;
    private boolean tileBottom = false;
    private boolean tileTop = false;



    @Override
    public void create() {
        position = new PositionComponent(40, 40);
        body = new BodyComponent(16, 16);
        velocity = new VelocityComponent();
        animation = new AttachedAnimatedSpriteComponent();
        attachmentInventory = new AttachmentInventoryComponent();
        spellInventory = new SpellInventoryComponent();
        health = new HealthComponent();
        mana = new ManaComponent();

        magic_staff = new SpellAttachment(new TextureComponent(new Texture("weapons/magic_staff.png")));

        attachmentInventory.attachments.add(magic_staff);
        attachmentInventory.attachments.add(magic_staff);
        attachmentInventory.attachments.add(magic_staff);
        attachmentInventory.attachments.add(magic_staff);
        attachmentInventory.equipped = magic_staff;
        attachmentInventory.selectedSpell = Consts.Spells.fireball;

        spellInventory.spells.add(Consts.Spells.fireball);
        spellInventory.spells.add(Consts.Spells.lightning_strike);
        spellInventory.spells.add(Consts.Spells.iceball);
        spellInventory.spells.add(Consts.Spells.iceball);
    }

    public void draw(DotD game) {
        TextureRegion frame = animation.currentAnimation.getAnimation().getKeyFrame(game.statetime);

        if (animation.currentAnimation.shouldRenderOnTop()) {
            game.batch.draw(frame, position.x + 8, position.y, body.width, body.height);
            magic_staff.draw(game.batch, animation.currentAnimation.getRotation());
        } else {
            magic_staff.draw(game.batch, animation.currentAnimation.getRotation());
            game.batch.draw(frame, position.x + 8, position.y, body.width, body.height);
        }

    }

    public void update(DotD game) {
        float expX = 0, expY = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            if ((Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) && !tileTop) {
                expY += 1;
            }
            if ((Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) && !tileBottom) {
                expY -= 1;
            }
            if ((Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) && !tileLeft) {
                expX -= 1;
            }
            if ((Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && !tileRight) {
                expX += 1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                magic_staff.use(game);
            }
        }

        velocity.vector.set(expX, expY);
        velocity.vector.nor();

        LocationSystem.addVelocity(position, velocity);

        animation.currentAnimation = AnimationSystem.getSpriteAnimation(animation, velocity);

        Vector2 loc = animation.currentAnimation.getWorldAttachmentLocation(game.statetime, this);
        PositionComponent loc2 = new PositionComponent();
        loc2.x = loc.x;
        loc2.y = loc.y;
        
        magic_staff.position = loc2;
        magic_staff.update(game);
    }

    public void checkPlayerCollisions(TiledMapTileLayer collisionObjectLayer) {
        int columns = collisionObjectLayer.getWidth();
        int rows = collisionObjectLayer.getHeight();

        int cellWidth = collisionObjectLayer.getTileWidth();
        int cellHeight = collisionObjectLayer.getTileHeight();

        int playerTileX = (int) (position.x / cellWidth) + 1;
        int playerTileY = (int) (position.y / cellHeight);

        TiledMapTileLayer.Cell leftCell = collisionObjectLayer.getCell(playerTileX, playerTileY);
        TiledMapTileLayer.Cell rightCell = collisionObjectLayer.getCell(playerTileX + 1, playerTileY);
        TiledMapTileLayer.Cell bottomCell = collisionObjectLayer.getCell(playerTileX, playerTileY - 1);
        TiledMapTileLayer.Cell topCell = collisionObjectLayer.getCell(playerTileX, playerTileY +     1);

        System.out.println(playerTileX + ", " + playerTileY);

        if (topCell != null) {
            System.out.println(topCell);
        }



        tileLeft = leftCell != null;

        tileRight = rightCell != null;

        tileBottom = bottomCell != null;

        tileTop = topCell != null;
    }


    public void dispose() {
        AnimationSystem.disposeSpriteAnimation(animation);
    }

    @Override
    public String toString() {
        return "Player{" +
                "x=" + position.x +
                ", y=" + position.y +
                ", velocity=" + velocity.vector +
                '}';
    }
}
