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
import com.shoebob.dotd.util.CameraShake;

// TODO: Draw a dungeon that has collisions
public class DotDGame extends ApplicationAdapter {
	private SpriteBatch batch;
	public static Player player; // should be accessible from other classes
	private TiledMap tiledMap;
	private TiledMapTileLayer water;
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer mapRenderer;

	private VfxManager vfxManager;
	private OldTvEffect tvEffect;
	private CrtEffect crtEffect;

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

		vfxManager = new VfxManager(Pixmap.Format.RGBA8888);
		tvEffect = new OldTvEffect();
		crtEffect = new CrtEffect();
		vfxManager.addEffect(tvEffect);
		vfxManager.addEffect(crtEffect);

		camera.position.set(0, 0, 0);
		camera.update();
	}

	@Override
	public void render() {
		ScreenUtils.clear(0f, 0, 0f, 0);
		vfxManager.cleanUpBuffers();
		vfxManager.beginInputCapture();

		camera.position.x = player.position.x;
		camera.position.y = player.position.y;

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

		player.update();
		player.draw(batch);
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
