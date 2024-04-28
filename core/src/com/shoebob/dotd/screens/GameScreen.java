package com.shoebob.dotd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;
import com.crashinvaders.vfx.VfxManager;
import com.crashinvaders.vfx.effects.CrtEffect;
import com.crashinvaders.vfx.effects.OldTvEffect;
import com.shoebob.dotd.entities.ui.MainInventoryBar;
import com.shoebob.dotd.entities.ui.SpellInventoryBar;
import com.shoebob.dotd.game.Consts;
import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.managers.ProjectileManager;
import com.shoebob.dotd.util.CameraShake;

public class GameScreen implements Screen {
    final DotD game;

    public TiledMap map;

    public OrthogonalTiledMapRenderer mapRenderer;

    // vfx shader stuff
    private VfxManager vfxManager;
    private OldTvEffect tvEffect;
    private CrtEffect crtEffect;

    // ui
    private MainInventoryBar mainInventoryBar;

    private SpellInventoryBar spellInventoryBar;


    public GameScreen(DotD game) {
        this.game = game;

        map = new TmxMapLoader().load("maps/testmap.tmx");

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

    }

    @Override
    public void show() {
        System.out.println("Game!");
        System.out.println(Consts.Spells.fireball);
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

        // START UPDATES
        game.player.update(game);
        game.player.draw(game);

        ProjectileManager.update(game);

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
}