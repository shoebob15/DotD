package com.shoebob.dotd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.crashinvaders.vfx.VfxManager;
import com.crashinvaders.vfx.effects.CrtEffect;
import com.crashinvaders.vfx.effects.OldTvEffect;
import com.shoebob.dotd.entities.Player;
import com.shoebob.dotd.entities.ui.InventoryBar;
import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.managers.ProjectileManager;
import com.shoebob.dotd.util.CameraShake;

public class GameScreen implements Screen {
    final DotD game;

    public Player player;

    public TiledMap map;

    public OrthographicCamera camera;

    public OrthogonalTiledMapRenderer mapRenderer;

    // vfx shader stuff
    private VfxManager vfxManager;
    private OldTvEffect tvEffect;
    private CrtEffect crtEffect;

    // ui
    private InventoryBar inventoryBar;

    public float statetime;



    public GameScreen(DotD game) {
        this.game = game;

        float h = Gdx.graphics.getHeight();
        float w = Gdx.graphics.getWidth();

        player = new Player();
        player.create();

        map = new TmxMapLoader().load("maps/testmap.tmx");

        camera = new OrthographicCamera(300, 300 * (h / w));
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        inventoryBar = new InventoryBar();
        inventoryBar.create();

        vfxManager = new VfxManager(Pixmap.Format.RGBA8888);
        tvEffect = new OldTvEffect();
        crtEffect = new CrtEffect();
        vfxManager.addEffect(tvEffect);
        vfxManager.addEffect(crtEffect);

        camera.position.set(0, 0, 0);
        camera.update();

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

        camera.position.x = player.position.x + 8;
        camera.position.y = player.position.y + 8;

        if (CameraShake.getTime() > 0) {
            CameraShake.tick();
            camera.translate(CameraShake.getPos());
            System.out.println("translating");
        }

        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        // START UPDATES
        player.update();
        player.draw(game.batch);

        ProjectileManager.update();

        inventoryBar.update();

        // END UPDATES

        game.batch.end();
        statetime += Gdx.graphics.getDeltaTime();

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
        player.dispose();
        map.dispose();

        vfxManager.dispose();
        tvEffect.dispose();
    }
}
