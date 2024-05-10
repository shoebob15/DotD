package com.shoebob.dotd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.crashinvaders.vfx.VfxManager;
import com.crashinvaders.vfx.effects.CrtEffect;
import com.crashinvaders.vfx.effects.OldTvEffect;
import com.shoebob.dotd.entities.enemies.EnemyEntity;
import com.shoebob.dotd.entities.ui.MainInventoryBar;
import com.shoebob.dotd.entities.ui.SpellInventoryBar;
import com.shoebob.dotd.game.Consts;
import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.managers.ProjectileManager;
import com.shoebob.dotd.managers.SpellEntityManager;
import com.shoebob.dotd.managers.EnemyManager;
import com.shoebob.dotd.systems.ManaSystem;
import com.shoebob.dotd.util.CameraShake;

public class GameScreen implements Screen {
    final DotD game;

    public TiledMap map;

    public OrthogonalTiledMapRenderer mapRenderer;

    TiledMapTileLayer collisionObjectLayer;
    MapObjects objects;

    // vfx shader stuff
    private VfxManager vfxManager;
    private OldTvEffect tvEffect;
    private CrtEffect crtEffect;

    // ui
    private MainInventoryBar mainInventoryBar;

    private SpellInventoryBar spellInventoryBar;



    public GameScreen(DotD game) {
        this.game = game;

        map = new TmxMapLoader().load("maps/build.tmx");

        collisionObjectLayer = (TiledMapTileLayer)map.getLayers().get("walls");

        objects = collisionObjectLayer.getObjects();

        System.out.println(objects.getCount());

        mapRenderer = new OrthogonalTiledMapRenderer(map);

        mainInventoryBar = new MainInventoryBar();
        mainInventoryBar.create();

        mainInventoryBar.loadInventory(game.player.attachmentInventory);

        spellInventoryBar = new SpellInventoryBar();
        spellInventoryBar.create();

        spellInventoryBar.loadInventory(game.player.spellInventory);

        vfxManager = new VfxManager(Pixmap.Format.RGBA8888);
        tvEffect = new OldTvEffect();
        crtEffect = new CrtEffect();
        vfxManager.addEffect(tvEffect);
        vfxManager.addEffect(crtEffect);

        game.camera.position.set(0, 0, 0);
        game.camera.update();

        game.camera = new OrthographicCamera(200,
                200 * ((float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth())
        );

        EnemyManager.addEnemy(new EnemyEntity());
        EnemyManager.addEnemy(new EnemyEntity());
        EnemyManager.addEnemy(new EnemyEntity());
    }

    @Override
    public void show() {
        System.out.println("Game!");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f, 0, 0f, 0);
        vfxManager.cleanUpBuffers();
        vfxManager.beginInputCapture();

        game.camera.position.x = game.player.position.x + 8;
        game.camera.position.y = game.player.position.y + 8;

        if (CameraShake.getTime() > 0) {
            CameraShake.tick(game);
            game.camera.translate(CameraShake.getPos());
            System.out.println("translating");
        }

        game.camera.update();
        mapRenderer.setView(game.camera);
        mapRenderer.render();

        game.batch.setProjectionMatrix(game.camera.combined);
        game.shape.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        game.shape.begin(ShapeRenderer.ShapeType.Filled);

        game.player.checkPlayerCollisions(collisionObjectLayer);

        // START UPDATES
        game.player.update(game);
        game.player.draw(game);

        ProjectileManager.update(game);
        SpellEntityManager.update(game);
        EnemyManager.update(game);
        ManaSystem.updateMana(game.player);

        drawPlayerInfo();

        mainInventoryBar.update(game);
        spellInventoryBar.update(game);

        // END UPDATES


        game.batch.end();
        game.shape.end();
        game.statetime += Gdx.graphics.getDeltaTime();

        vfxManager.endInputCapture();
        vfxManager.applyEffects();
        vfxManager.renderToScreen();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.batch.dispose();
        game.player.dispose();
        map.dispose();

        vfxManager.dispose();
        tvEffect.dispose();
    }

    private void drawPlayerInfo() {
        // render player health and mana
        game.font.getData().setScale(.25f);
        game.font.setUseIntegerPositions(false); // removes weird shaking due to int accuracy

        game.font.setColor(Color.WHITE);
        game.font.draw(game.batch, "HEALTH - ", game.camera.position.x - 95, game.camera.position.y + 70);
        game.font.setColor(.8f, 0, 0, 1);
        game.font.draw(game.batch, game.player.health.health + "", game.camera.position.x - 40, game.camera.position.y + 70);

        game.font.setColor(Color.WHITE);
        game.font.draw(game.batch, "MANA - ", game.camera.position.x - 95, game.camera.position.y + 60);
        game.font.setColor(0f, 1, 1, 1);
        game.font.draw(game.batch, game.player.mana.currentMana + "", game.camera.position.x - 50, game.camera.position.y + 60);
    }


}
