package com.shoebob.dotd;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.decals.SimpleOrthoGroupStrategy;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sun.org.apache.xpath.internal.operations.Or;

// TODO: Draw a dungeon that has collisions
public class DotDGame extends ApplicationAdapter {
	static final int WORLD_HEIGHT = 20;
	static final int WORLD_WIDTH = 30;

	private SpriteBatch batch;
	private Player player;
	private TiledMap tiledMap;
	private TiledMapTileLayer water;
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer mapRenderer;
	
	@Override
	public void create() {
		float h = Gdx.graphics.getHeight();
		float w = Gdx.graphics.getWidth();

		batch = new SpriteBatch();
		player = new Player();
		tiledMap = new TmxMapLoader().load("maps/testmap.tmx");
		water = (TiledMapTileLayer)tiledMap.getLayers().get(0);
		camera = new OrthographicCamera(300, 300 * (h / w));
		mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();
	}

	@Override
	public void render() {
		ScreenUtils.clear(.12f, .6f, .7f, 1);
		camera.position.x = player.x;
		camera.position.y = player.y;
		camera.update();
		mapRenderer.setView(camera);
		mapRenderer.render();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		// TODO: Somehow attach weapon to player. Need a ROBUST system with animation, since
		// TODO: there will be many more weapons and objects
		batch.draw(new Texture(Gdx.files.internal("weapons/sword.png")), 0, 0);
		player.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		player.dispose();
		tiledMap.dispose();
	}
}
