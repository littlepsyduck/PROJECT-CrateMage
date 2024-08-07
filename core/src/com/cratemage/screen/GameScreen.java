package com.cratemage.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cratemage.CrateMage;
import com.cratemage.controller.ButtonManager;
import com.cratemage.controller.DataProcess;
import com.cratemage.controller.MyContactListener;
import com.cratemage.controller.TileMapHelper;
import com.cratemage.model.Box;
import com.cratemage.model.Goal;
import com.cratemage.model.Player;
import com.cratemage.view.Hud;

import java.util.ArrayList;

import static com.cratemage.common.constant.GameConstant.*;
import static java.lang.Integer.max;
import static java.lang.Math.min;

public class GameScreen implements Screen {
    public float stateTime;
    public CrateMage game;
    public World world;
    public Player player;
    public ArrayList<Box> boxes = new ArrayList<>();
    public TileMapHelper tileMapHelper;
    public TiledMap map;
    public OrthogonalTiledMapRenderer renderer;
    public Box2DDebugRenderer box2DDebugRenderer;
    public Goal goal;
    public Hud hud;

    MyContactListener listener;

    public int[] Layer1 = new int[]{0}, Layer2 = new int[]{1}, Layer3 = new int[]{3}; // Lấy index của layer

    //--HTH
    private Stage stage;
    private ButtonManager buttonManager;
    public DataProcess dataProcess = new DataProcess();

    public GameScreen(CrateMage game) {
        this.world = new World(new Vector2(0, 0), false);
        this.game = game;

        this.box2DDebugRenderer = new Box2DDebugRenderer();
        box2DDebugRenderer.setDrawBodies(false);
        box2DDebugRenderer.setDrawJoints(false);
        box2DDebugRenderer.setDrawContacts(false);

        String fileNameMap = "Map/map_" + game.levelCurrent + ".tmx";
        map = new TmxMapLoader().load(fileNameMap);
        this.tileMapHelper = new TileMapHelper(this, fileNameMap);
        this.renderer = tileMapHelper.setupMap();

        listener = new MyContactListener(game, world, boxes, goal, player);
        world.setContactListener(listener);

        hud = new Hud(game, game.batch);

    }

    @Override
    public void show() {

//        staticCamera = new OrthographicCamera(512, 360);

        game.camera = new OrthographicCamera(WINDOW_WIDTH / 5f, WINDOW_HEIGHT / 5f);


        // -----DUNG NHAC
        game.stopAllMusic();
        game.playMainMusic();

        //--HTH
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        buttonManager = new ButtonManager(game);
        Button homeButton = buttonManager.createHomeButton();
        Button menuButton = buttonManager.createMenuButton();
        Button resetButton = buttonManager.creatResetButton();
        Button musicButton = buttonManager.createMusicButton();

        stage.addActor(homeButton);
        stage.addActor(menuButton);
        stage.addActor(resetButton);
        stage.addActor(musicButton);
    }

    public void update(float dt) {
        world.step(1 / 60f, 6, 2);

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
        for (Box box : boxes) {
            box.update(dt);
        }
        int numpadPressed = Input.Keys.NUMPAD_0;
        int numPress = Input.Keys.NUM_0;
        for(int i = 0; i <= min(9, boxes.size() - 1); i++){
            if(Gdx.input.isKeyJustPressed(numPress + i) || Gdx.input.isKeyJustPressed(numpadPressed + i)){
                boxes.get(i).setChoose(!boxes.get(i).isChoose());
            }
        }

        game.camera.update();
        hud.update(dt);

        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            game.setScreen(new GameScreen(game));
        }

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(game.camera);
        // render map theo layer index
        renderer.render(Layer1);
        renderer.render(Layer2);
        renderer.render(Layer3);

        box2DDebugRenderer.render(world, game.camera.combined.scl(PPM));
//        box2DDebugRenderer.render(world, staticCamera.combined.scl(PPM));

        stateTime += delta;

        game.batch.begin();
        game.batch.setProjectionMatrix(game.camera.combined);

        this.update(delta);

        for (Box box : boxes) {
            box.draw(game.batch);
        }

        player.draw(game.batch);
        game.batch.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        if(listener.completed) {
            game.time = hud.getTime();
            game.levelUnlocked = max(game.levelCurrent + 1, game.levelUnlocked);
            dataProcess.writeResult(game.levelCurrent, game.namePlayer, game.time);
            //System.out.println(game.time);
            game.setScreen(new LevelCompletedScreen(game));
        }
        // HTH
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
        stage.dispose();
        renderer.dispose();
        box2DDebugRenderer.dispose();
        hud.dispose();
        world.dispose();
    }
}