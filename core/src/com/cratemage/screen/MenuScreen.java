package com.cratemage.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.cratemage.CrateMage;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MenuScreen implements Screen {

    private static final int PLAY1_BUTTON_WIDTH = 360;
    private static final int PLAY1_BUTTON_HEIGHT = 120;
    private static final int PLAY2_BUTTON_WIDTH = 360;
    private static final int PLAY2_BUTTON_HEIGHT = 120;
    private static final int NAME_BUTTON_WIDTH = 580;
    private static final int NAME_BUTTON_HEIGHT = 330;
    private static final int MENU1_BUTTON_WIDTH = 50;
    private static final int MENU1_BUTTON_HEIGHT = 50;
    private static final int MENU2_BUTTON_WIDTH = 50;
    private static final int MENU2_BUTTON_HEIGHT = 50;

    private OrthographicCamera camera;
    private Music clickSound;
    private Music clickSound1;

    private boolean playButtonPreviouslyTouched = false;
    private boolean continueButtonPreviouslyTouched = false;
    private boolean tutorialButtonPreviouslyTouched = false;
    private boolean exitButtonPreviouslyTouched = false;
    private boolean menuhomeButtonPreviouslyTouched = false;

    CrateMage game;

    Texture namegame;
    Texture exitButtonInactive;
    Texture exitButtonActive;
    Texture playButtonInactive;
    Texture playButtonActive;
    Texture continueButtonInactive;
    Texture continueButtonActive;
    Texture tutorialButtonInactive;
    Texture tutorialButtonActive;

    Texture menuhomeButtonInactive;
    Texture menuhomeButtonActive;

    public MenuScreen(CrateMage game) {
        this.game = game;
        namegame = new Texture("menu/namegame.png");
        playButtonActive = new Texture("menu/newgame.png");
        playButtonInactive = new Texture("menu/innewgame.png");
        continueButtonActive= new Texture("menu/continue.png");
        continueButtonInactive = new Texture("menu/incontinue.png");
        tutorialButtonActive= new Texture("menu/tutorial.png");
        tutorialButtonInactive = new Texture("menu/intutorial.png");
        exitButtonActive= new Texture("menu/exit.png");
        exitButtonInactive = new Texture("menu/inexit.png");

        //-- button menu
        menuhomeButtonActive= new Texture("menu/home.png");
        menuhomeButtonInactive = new Texture("menu/inhome.png");

        //---sound button
        clickSound = Gdx.audio.newMusic(Gdx.files.internal("Sound/MouseClick.mp3"));
        clickSound1 = Gdx.audio.newMusic(Gdx.files.internal("Sound/mousetouch.mp3"));

    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        game.playBackgroundMusic();

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        ScreenUtils.clear(1, 1, 1, 0);

        game.batch.begin();
        Texture background = new Texture("menu/backgr.png");
        game.batch.draw(background, 0, 0, 1280, 720);
        game.batch.draw(namegame,1280-NAME_BUTTON_WIDTH-100,720-NAME_BUTTON_HEIGHT+10,NAME_BUTTON_WIDTH,NAME_BUTTON_HEIGHT);

        //--- NEWGAME

        int x = 720/4-100;
        int y = 720-90-PLAY1_BUTTON_HEIGHT;  //720-480-(3*20)

        Vector3 touchPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPoint); // Chuyển đổi tọa độ

        boolean isTouchingButton = touchPoint.x < x + PLAY1_BUTTON_WIDTH &&
                touchPoint.x > x &&
                touchPoint.y > y &&
                touchPoint.y < y + PLAY1_BUTTON_HEIGHT;

        if (isTouchingButton) {
            game.batch.draw(playButtonActive, x, y, PLAY2_BUTTON_WIDTH, PLAY2_BUTTON_HEIGHT);

            if (!playButtonPreviouslyTouched) {
                clickSound1.play();
                playButtonPreviouslyTouched = true;
            }

            if (Gdx.input.isTouched()) {
                clickSound.play();
                this.dispose();
                game.setScreen(new LevelSelectScreen(game));
            }
        } else {
            game.batch.draw(playButtonInactive, x, y, PLAY1_BUTTON_WIDTH, PLAY1_BUTTON_HEIGHT);
            playButtonPreviouslyTouched = false;
        }

        //-- CONTINUE

        // x = 1720/4-100;
        y = 720-90-PLAY1_BUTTON_HEIGHT*2-20;

        isTouchingButton = touchPoint.x < x + PLAY1_BUTTON_WIDTH &&
                touchPoint.x > x &&
                touchPoint.y > y &&
                touchPoint.y < y + PLAY1_BUTTON_HEIGHT;

        if (isTouchingButton) {
            game.batch.draw(continueButtonActive, x, y, PLAY2_BUTTON_WIDTH, PLAY2_BUTTON_HEIGHT);

            if (!continueButtonPreviouslyTouched) {
                clickSound1.play();
                continueButtonPreviouslyTouched = true;
            }

            if (Gdx.input.isTouched()) {
                clickSound.play();
                this.dispose();
                game.setScreen(new GameScreen(game));
            }

        } else {
            game.batch.draw(continueButtonInactive, x, y, PLAY1_BUTTON_WIDTH, PLAY1_BUTTON_HEIGHT);
            continueButtonPreviouslyTouched = false;
        }

        //-- TUTORIAL

        y = 720-90-PLAY1_BUTTON_HEIGHT*3-20*2;

        isTouchingButton = touchPoint.x < x + PLAY1_BUTTON_WIDTH &&
                touchPoint.x > x &&
                touchPoint.y > y &&
                touchPoint.y < y + PLAY1_BUTTON_HEIGHT;

        if (isTouchingButton)  {

            game.batch.draw(tutorialButtonActive, x, y, PLAY2_BUTTON_WIDTH, PLAY2_BUTTON_HEIGHT);

            if (!tutorialButtonPreviouslyTouched) {
                clickSound1.play();
                tutorialButtonPreviouslyTouched = true;
            }

            if (Gdx.input.isTouched()) {
                clickSound.play();
                this.dispose();
                game.setScreen(new GameScreen(game));
            }
        } else {
            game.batch.draw(tutorialButtonInactive, x, y, PLAY1_BUTTON_WIDTH, PLAY1_BUTTON_HEIGHT);
            tutorialButtonPreviouslyTouched = false;

        }

        // ----EXIT
        y = 720-90-PLAY1_BUTTON_HEIGHT*4-20*3; // 20 là khoảng cách các button

        isTouchingButton = touchPoint.x < x + PLAY1_BUTTON_WIDTH &&
                touchPoint.x > x &&
                touchPoint.y > y &&
                touchPoint.y < y + PLAY1_BUTTON_HEIGHT;

        if (isTouchingButton) {
            game.batch.draw(exitButtonActive, x, y, PLAY2_BUTTON_WIDTH, PLAY2_BUTTON_HEIGHT);

            if (!exitButtonPreviouslyTouched) {
                clickSound1.play();
                exitButtonPreviouslyTouched = true;
            }

            if (Gdx.input.isTouched()) {
                clickSound.play();
                Gdx.app.exit();
                game.setScreen(new GameScreen(game));
            }
        } else {
            game.batch.draw(exitButtonInactive, x, y, PLAY1_BUTTON_WIDTH, PLAY1_BUTTON_HEIGHT);
            exitButtonPreviouslyTouched = false;
        }
        //____ MENU_BUTTON
        //--home

        x = 1280-60;
        y = 720-60;

        isTouchingButton = touchPoint.x < x + MENU1_BUTTON_WIDTH &&
                touchPoint.x > x &&
                touchPoint.y > y &&
                touchPoint.y < y + MENU1_BUTTON_HEIGHT;

        if (isTouchingButton) {
            game.batch.draw(menuhomeButtonActive, x, y, MENU2_BUTTON_WIDTH, MENU2_BUTTON_HEIGHT);

            if (!menuhomeButtonPreviouslyTouched) {
                clickSound1.play();
                menuhomeButtonPreviouslyTouched = true;
            }

            if (Gdx.input.justTouched()) {
                clickSound.play();
                this.dispose();
                game.setScreen(new MenuScreen(game));
            }

        } else {
            game.batch.draw(menuhomeButtonInactive, x, y, MENU1_BUTTON_WIDTH, MENU1_BUTTON_HEIGHT);
            menuhomeButtonPreviouslyTouched = false;
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
      //  playButtonActive.dispose();
        // playButtonInactive.dispose();
       // exitButtonActive.dispose();
        // exitButtonInactive.dispose();
    }
}
