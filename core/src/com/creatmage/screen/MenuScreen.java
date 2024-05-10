package com.creatmage.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.creatmage.MyGdxGame;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MenuScreen implements Screen {

    private static final int PLAY1_BUTTON_WIDTH = 360;
    private static final int PLAY1_BUTTON_HEIGHT = 120;
    private static final int PLAY2_BUTTON_WIDTH = 360;
    private static final int PLAY2_BUTTON_HEIGHT = 120;
    private static final int NAME_BUTTON_WIDTH = 500;
    private static final int NAME_BUTTON_HEIGHT = 500;
    private OrthographicCamera camera;

    MyGdxGame game;

    Texture namegame;
    Texture exitButtonInactive;
    Texture exitButtonActive;
    Texture playButtonInactive;
    Texture playButtonActive;
    Texture continueButtonInactive;
    Texture continueButtonActive;
    Texture tutorialButtonInactive;
    Texture tutorialButtonActive;

    public MenuScreen(MyGdxGame game) {
        this.game = game;
        namegame = new Texture("namegame.png");
        playButtonActive = new Texture("newgame.png");
        playButtonInactive = new Texture("innewgame.png");
        continueButtonActive= new Texture("continue.png");
        continueButtonInactive = new Texture("incontinue.png");
        tutorialButtonActive= new Texture("tutorial.png");
        tutorialButtonInactive = new Texture("intutorial.png");
        exitButtonActive= new Texture("exit.png");
        exitButtonInactive = new Texture("inexit.png");
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        ScreenUtils.clear(1, 1, 1, 0);

        game.batch.begin();

        //--- Draw BACKGR ---s
        Texture background = new Texture("backgr.png");
        game.batch.draw(background, 0, 0, 1280, 720);
        game.batch.draw(namegame,1280-NAME_BUTTON_WIDTH-100,720-NAME_BUTTON_HEIGHT+10,NAME_BUTTON_WIDTH,NAME_BUTTON_HEIGHT);

        //--- NEWGAME

        int x = 720/4-100;
        int y = 720-90-PLAY1_BUTTON_HEIGHT;  //720-480-(3*20)

        Vector3 touchPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPoint); // Chuyen đoi toạ độ

        if (touchPoint.x < x + PLAY1_BUTTON_WIDTH &&
                touchPoint.x > x &&
                touchPoint.y > y &&
                touchPoint.y < y + PLAY1_BUTTON_HEIGHT) {
            game.batch.draw(playButtonActive, x, y, PLAY2_BUTTON_WIDTH, PLAY2_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new GameScreen(game));
            }
        } else {
            game.batch.draw(playButtonInactive, x, y, PLAY1_BUTTON_WIDTH, PLAY1_BUTTON_HEIGHT);
        }



        //-- CONTINUE

        // x = 1720/4-100;
        y = 720-90-PLAY1_BUTTON_HEIGHT*2-20;

        if (touchPoint.x < x + PLAY1_BUTTON_WIDTH &&
                touchPoint.x > x &&
                touchPoint.y > y &&
                touchPoint.y < y + PLAY1_BUTTON_HEIGHT) {
            game.batch.draw(continueButtonActive, x, y, PLAY2_BUTTON_WIDTH, PLAY2_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new GameScreen(game));
            }
        } else {
            game.batch.draw(continueButtonInactive, x, y, PLAY1_BUTTON_WIDTH, PLAY1_BUTTON_HEIGHT);
        }

        //-- TUTORIAL

        y = 720-90-PLAY1_BUTTON_HEIGHT*3-20*2;
        if (touchPoint.x < x + PLAY1_BUTTON_WIDTH &&
                touchPoint.x > x &&
                touchPoint.y > y &&
                touchPoint.y < y + PLAY1_BUTTON_HEIGHT) {
            game.batch.draw(tutorialButtonActive, x, y, PLAY2_BUTTON_WIDTH, PLAY2_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new TutorialScreen(game));
            }
        } else {
            game.batch.draw(tutorialButtonInactive, x, y, PLAY1_BUTTON_WIDTH, PLAY1_BUTTON_HEIGHT);
        }

        // ----EXIT
        y = 720-90-PLAY1_BUTTON_HEIGHT*4-20*3; // 20 la k/c cac button
        if (touchPoint.x < x + PLAY1_BUTTON_WIDTH &&
                touchPoint.x > x &&
                touchPoint.y > y &&
                touchPoint.y < y + PLAY1_BUTTON_HEIGHT) {
            game.batch.draw(exitButtonActive, x, y, PLAY2_BUTTON_WIDTH, PLAY2_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
                game.setScreen(new GameScreen(game));
            }
        } else {
            game.batch.draw(exitButtonInactive, x, y, PLAY1_BUTTON_WIDTH, PLAY1_BUTTON_HEIGHT);
        }




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

    }

    @Override
    public void dispose() {
//        playButtonActive.dispose();
//       // playButtonInactive.dispose();
//        exitButtonActive.dispose();
//       // exitButtonInactive.dispose();
    }
}
