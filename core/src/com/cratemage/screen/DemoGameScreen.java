package com.cratemage.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cratemage.Main;

public class DemoGameScreen implements Screen {
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    Texture img;
    float Speed = 75.0f;

    public static final float CHAR_ANIMATION_SPEED = 0.2f;
    public static final int CHAR_WIDTH = 24;
    public static final int CHAR_HEIGHT = 24;
    float imgx, imgy;

    int roll;
    Animation[] rolls;
    float stateTime;
    float prevx, prevy;

    Main game;

    public DemoGameScreen(Main game) {
        this.game = game;

        imgx=256+10;
        imgy=16+60+256;

        roll = 0;
        rolls = new Animation[1];
        img = new Texture("character/sheets/DinoSprites - tard.png");
        TextureRegion[][] rollSpriteSheet =TextureRegion.split(img, 24,24);

        rolls[roll] = new Animation(CHAR_ANIMATION_SPEED, rollSpriteSheet[0]);

    }

    @Override
    public void show() {
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("map/Map1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();

        if(Gdx.input.isKeyPressed(Input.Keys.W)||Gdx.input.isKeyPressed(Input.Keys.UP)){
            System.out.println("W");
            prevy = imgy;
            imgy+=Gdx.graphics.getDeltaTime()*Speed;
            //img = new Texture("character/gifs/DinoSprites_tard.gif");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)||Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            //      System.out.println("A");
            prevx = imgx;
            imgx-=Gdx.graphics.getDeltaTime()*Speed;
            //img = new Texture("character/gifs/DinoSprites_tard.gif");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)||Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            //     System.out.println("S");
            prevy = imgy;
            imgy-=Gdx.graphics.getDeltaTime()*Speed;
            //img = new Texture("character/gifs/DinoSprites_tard.gif");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)||Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            System.out.println("D");
            prevx = imgx;
            imgx+=Gdx.graphics.getDeltaTime()*Speed;
            //img = new Texture("character/gifs/DinoSprites_tard.gif");
        }
        if(!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
            img = new Texture("character/gifs/DinoSprites_tard.gif");
        }

        stateTime += delta;
        game.batch.begin();
        game.batch.draw((TextureRegion)rolls[roll].getKeyFrame(stateTime, true), imgx, imgy, CHAR_WIDTH, CHAR_HEIGHT);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.setToOrtho(false, 1280, 720);
        camera.update();
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
        map.dispose();
        renderer.dispose();
    }
}