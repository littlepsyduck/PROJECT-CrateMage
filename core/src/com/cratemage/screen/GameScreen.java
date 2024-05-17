package com.cratemage.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.cratemage.CrateMage;
import com.cratemage.controller.TileMapHelper;
import com.cratemage.model.Box;
import com.cratemage.model.Player;

import static com.cratemage.common.constant.GameConstant.PPM;

public class GameScreen implements Screen {
    public float stateTime;
    public CrateMage game;
    public World world;
    public Player player;
    public Box box;
    public TileMapHelper tileMapHelper;
    public TiledMap map = new TmxMapLoader().load("Map/map_1.tmx");
    public OrthogonalTiledMapRenderer renderer;
    public Box2DDebugRenderer box2DDebugRenderer;
    public OrthographicCamera staticCamera;
    public OrthographicCamera playerCamera;

    public int[] Layer1 = new int[]{0}, Layer2 = new int[]{3}, Layer3 = new int[]{1}; // Lấy index của layer
    public GameScreen(CrateMage game){
        this.world = new World(new Vector2(0,0), false);
        this.game = game;
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        box2DDebugRenderer.setDrawBodies(false);
        box2DDebugRenderer.setDrawJoints(false);
        box2DDebugRenderer.setDrawContacts(false);
        this.tileMapHelper = new TileMapHelper(this);
        this.renderer = tileMapHelper.setupMap();

    }
    @Override
    public void show() {
//        staticCamera = new OrthographicCamera(512, 360);
        game.camera = new OrthographicCamera(430,240);

    }
    public void update(float dt){
        world.step(1/60f, 6, 2);

        Vector3 position = game.camera.position;
        position.x = player.body.getPosition().x * PPM * 10 / 10f;
        position.y = player.body.getPosition().y * PPM * 10 / 10f;
        game.camera.position.set(position);
//        staticCamera.position.set(position);
        if (game.camera.position.x < game.camera.viewportWidth / 2) {
            game.camera.position.x = game.camera.viewportWidth / 2;
        }
        if (game.camera.position.x > map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class) - game.camera.viewportWidth / 2) {
            game.camera.position.x = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class) - game.camera.viewportWidth / 2;
        }
        if (game.camera.position.y < game.camera.viewportHeight / 2) {
            game.camera.position.y = game.camera.viewportHeight / 2;
        }
        if (game.camera.position.y > map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class) - game.camera.viewportHeight / 2) {
            game.camera.position.y = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class) - game.camera.viewportHeight / 2;
        }
        player.update(dt);
        box.update(dt);
        game.camera.update();
//        staticCamera.update();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(game.camera);
        // render map theo layer index
        renderer.render(Layer1);
        renderer.render(Layer3);
//        renderer.render(Layer2);

        box2DDebugRenderer.render(world, game.camera.combined.scl(PPM));
//        box2DDebugRenderer.render(world, staticCamera.combined.scl(PPM));

        stateTime += delta;

//        game.batch.setProjectionMatrix(staticCamera.combined);

        game.batch.begin();
        game.batch.setProjectionMatrix(game.camera.combined);
        this.update(delta);
        box.draw(game.batch);
        player.draw(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        box2DDebugRenderer.dispose();
    }
}
