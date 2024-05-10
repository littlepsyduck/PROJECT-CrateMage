package com.creatmage.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.creatmage.MyGdxGame;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class TutorialScreen implements Screen {

    private static final int PLAY1_BUTTON_WIDTH = 70;
    private static final int PLAY1_BUTTON_HEIGHT = 70;
    private static final int PLAY2_BUTTON_WIDTH = 70;
    private static final int PLAY2_BUTTON_HEIGHT = 70;

    private OrthographicCamera camera;
    private MyGdxGame game;
    private Texture tutorialBackground;
    Texture homeButtonInactive;
    Texture homeButtonActive;


    public TutorialScreen(MyGdxGame game) {
        this.game = game;
        tutorialBackground = new Texture("backgr_tutorial.png");
        homeButtonActive = new Texture("home.png");
        homeButtonInactive = new Texture("inhome.png");
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        ScreenUtils.clear(1, 1, 1, 0);

        game.batch.begin();
        game.batch.draw(tutorialBackground, 0, 0, 1280, 720);

        //--- NEWGAME

        int x = PLAY1_BUTTON_WIDTH-50;
        int y = PLAY1_BUTTON_HEIGHT-50;

        Vector3 touchPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPoint); // Chuyen đoi toạ độ

        if (touchPoint.x < x + PLAY1_BUTTON_WIDTH &&
                touchPoint.x > x &&
                touchPoint.y > y &&
                touchPoint.y < y + PLAY1_BUTTON_HEIGHT) {
            game.batch.draw(homeButtonActive, x, y, PLAY2_BUTTON_WIDTH, PLAY2_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new MenuScreen(game));
            }
        } else {
            game.batch.draw(homeButtonInactive, x, y, PLAY1_BUTTON_WIDTH, PLAY1_BUTTON_HEIGHT);
        }

        game.batch.end();
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
    }
}
