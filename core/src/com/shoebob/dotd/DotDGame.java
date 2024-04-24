package com.shoebob.dotd;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.crashinvaders.vfx.VfxManager;
import com.crashinvaders.vfx.effects.OldTvEffect;
import com.crashinvaders.vfx.effects.CrtEffect;
import com.shoebob.dotd.entities.Player;
import com.shoebob.dotd.entities.ui.InventoryBar;
import com.shoebob.dotd.entities.ui.OverlayEntity;
import com.shoebob.dotd.managers.ProjectileManager;
import com.shoebob.dotd.util.CameraShake;

import javax.swing.*;

// TODO: Draw a dungeon that has collisions
public class DotDGame extends ApplicationAdapter {
	// the spritebatch renderer
	public static SpriteBatch batch;

	// the player object
	public static Player player;

	// the map imported from Tiled
	private TiledMap tiledMap;

	// TODO the water layer - cannot traverse
	private TiledMapTileLayer water;

	// screen camera
	public static OrthographicCamera camera;

	// object for rendering map
	private OrthogonalTiledMapRenderer mapRenderer;

	// vfx shader stuff
	private VfxManager vfxManager;
	private OldTvEffect tvEffect;
	private CrtEffect crtEffect;

	// ui stuff
	InventoryBar inventoryBar;

	// stores the statetime
	public static float statetime;
	
	@Override
	public void create() {
		float h = Gdx.graphics.getHeight();
		float w = Gdx.graphics.getWidth();

		batch = new SpriteBatch();
		player = new Player();
		player.create();

		tiledMap = new TmxMapLoader().load("maps/testmap.tmx");
		water = (TiledMapTileLayer)tiledMap.getLayers().get(0);
		camera = new OrthographicCamera(300, 300 * (h / w));
		mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

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

	// acts as update method (it should be called that)
	@Override
	public void render() {

		System.out.println(camera.position);
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

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		// START UPDATES
		player.update();
		player.draw(batch);

		ProjectileManager.update();

		inventoryBar.update();

		// END UPDATES

		batch.end();
		statetime += Gdx.graphics.getDeltaTime();

		vfxManager.endInputCapture();
		vfxManager.applyEffects();
		vfxManager.renderToScreen();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		player.dispose();
		tiledMap.dispose();

		vfxManager.dispose();
		tvEffect.dispose();
	}
}
