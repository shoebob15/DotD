package com.shoebob.dotd;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.shoebob.dotd.entities.Player;
import com.shoebob.dotd.util.CameraShake;
import com.shoebob.dotd.util.Consts;

import java.util.concurrent.CancellationException;

// TODO: Draw a dungeon that has collisions
public class DotDGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Player player;
	private TiledMap tiledMap;
	private TiledMapTileLayer water;
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer mapRenderer;

	public static float statetime;
	
	@Override
	public void create() {
		float h = Gdx.graphics.getHeight();
		float w = Gdx.graphics.getWidth();

		batch = new SpriteBatch();
		player = new Player(0, 0, 32, 32,
				Consts.Animations.playerIdleAnimation, Consts.Animations.playerWalkRAnimation,
				Consts.Animations.playerWalkLAnimation, Consts.Animations.playerWalkBAnimation,
				Consts.Animations.playerWalkFAnimation);

		tiledMap = new TmxMapLoader().load("maps/testmap.tmx");
		water = (TiledMapTileLayer)tiledMap.getLayers().get(0);
		camera = new OrthographicCamera(300, 300 * (h / w));
		mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();
	}

	@Override
	public void render() {
		ScreenUtils.clear(0f, 0, 0f, 0);
		camera.position.x = player.getX();
		camera.position.y = player.getY();

		if (CameraShake.getTime() > 0) {
			CameraShake.tick(statetime);
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
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		player.dispose();
		tiledMap.dispose();
	}
}
