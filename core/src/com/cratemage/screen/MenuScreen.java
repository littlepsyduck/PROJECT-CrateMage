package com.cratemage.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.ScreenUtils;
import com.cratemage.CrateMage;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {

    private static final int PLAY1_BUTTON_WIDTH = 360;
    private static final int PLAY1_BUTTON_HEIGHT = 120;
    private static final int PLAY2_BUTTON_WIDTH = 360;
    private static final int PLAY2_BUTTON_HEIGHT = 120;
    private static final int NAME_BUTTON_WIDTH = 580;
    private static final int NAME_BUTTON_HEIGHT = 330;

    private OrthographicCamera camera;
    private Music clickSound;
    private Music clickSound1;
    private ButtonManager buttonManager;

    private boolean playButtonPreviouslyTouched = false;
    private boolean continueButtonPreviouslyTouched = false;
    private boolean tutorialButtonPreviouslyTouched = false;
    private boolean exitButtonPreviouslyTouched = false;

    CrateMage game;
    Stage stage;
    SpriteBatch batch;

    Texture namegame;
    Texture exitButtonInactive;
    Texture exitButtonActive;
    Texture playButtonInactive;
    Texture playButtonActive;
    Texture continueButtonInactive;
    Texture continueButtonActive;
    Texture tutorialButtonInactive;
    Texture tutorialButtonActive;

    public MenuScreen(CrateMage game) {
        this.game = game;
        namegame = new Texture("menu/namegame.png");
        playButtonActive = new Texture("menu/newgame.png");
        playButtonInactive = new Texture("menu/innewgame.png");
        continueButtonActive = new Texture("menu/continue.png");
        continueButtonInactive = new Texture("menu/incontinue.png");
        tutorialButtonActive = new Texture("menu/tutorial.png");
        tutorialButtonInactive = new Texture("menu/intutorial.png");
        exitButtonActive = new Texture("menu/exit.png");
        exitButtonInactive = new Texture("menu/inexit.png");

        //--- nút âm thanh
        clickSound = Gdx.audio.newMusic(Gdx.files.internal("Sound/MouseClick.mp3"));
        clickSound1 = Gdx.audio.newMusic(Gdx.files.internal("Sound/mousetouch.mp3"));

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        batch = (SpriteBatch) stage.getBatch();
        buttonManager = new ButtonManager(game);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        game.playBackgroundMusic();
        Button musicButton = buttonManager.createMusicButton();
        stage.addActor(musicButton);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        ScreenUtils.clear(1, 1, 1, 0);

        batch.begin();
        Texture background = new Texture("menu/backgr.png");
        batch.draw(background, 0, 0, 1280, 720);
        batch.draw(namegame, 1280 - NAME_BUTTON_WIDTH - 100, 720 - NAME_BUTTON_HEIGHT + 10, NAME_BUTTON_WIDTH, NAME_BUTTON_HEIGHT);

        //--- NÚT NEW GAME
        int x = 720 / 4 - 100;
        int y = 720 - 90 - PLAY1_BUTTON_HEIGHT;  //720-480-(3*20)

        Vector3 touchPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPoint); // Chuyển đổi tọa độ

        boolean isTouchingButton = touchPoint.x < x + PLAY1_BUTTON_WIDTH &&
                touchPoint.x > x &&
                touchPoint.y > y &&
                touchPoint.y < y + PLAY1_BUTTON_HEIGHT;

        if (isTouchingButton) {
            batch.draw(playButtonActive, x, y, PLAY2_BUTTON_WIDTH, PLAY2_BUTTON_HEIGHT);

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
            batch.draw(playButtonInactive, x, y, PLAY1_BUTTON_WIDTH, PLAY1_BUTTON_HEIGHT);
            playButtonPreviouslyTouched = false;
        }

        //-- NÚT CONTINUE
        y = 720 - 90 - PLAY1_BUTTON_HEIGHT * 2 - 20;

        isTouchingButton = touchPoint.x < x + PLAY1_BUTTON_WIDTH &&
                touchPoint.x > x &&
                touchPoint.y > y &&
                touchPoint.y < y + PLAY1_BUTTON_HEIGHT;

        if (isTouchingButton) {
            batch.draw(continueButtonActive, x, y, PLAY2_BUTTON_WIDTH, PLAY2_BUTTON_HEIGHT);

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
            batch.draw(continueButtonInactive, x, y, PLAY1_BUTTON_WIDTH, PLAY1_BUTTON_HEIGHT);
            continueButtonPreviouslyTouched = false;
        }

        //-- NÚT TUTORIAL
        y = 720 - 90 - PLAY1_BUTTON_HEIGHT * 3 - 20 * 2;

        isTouchingButton = touchPoint.x < x + PLAY1_BUTTON_WIDTH &&
                touchPoint.x > x &&
                touchPoint.y > y &&
                touchPoint.y < y + PLAY1_BUTTON_HEIGHT;

        if (isTouchingButton) {
            batch.draw(tutorialButtonActive, x, y, PLAY2_BUTTON_WIDTH, PLAY2_BUTTON_HEIGHT);

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
            batch.draw(tutorialButtonInactive, x, y, PLAY1_BUTTON_WIDTH, PLAY1_BUTTON_HEIGHT);
            tutorialButtonPreviouslyTouched = false;
        }

        // ---- NÚT EXIT
        y = 720 - 90 - PLAY1_BUTTON_HEIGHT * 4 - 20 * 3; // 20 là khoảng cách các nút

        isTouchingButton = touchPoint.x < x + PLAY1_BUTTON_WIDTH &&
                touchPoint.x > x &&
                touchPoint.y > y &&
                touchPoint.y < y + PLAY1_BUTTON_HEIGHT;

        if (isTouchingButton) {
            batch.draw(exitButtonActive, x, y, PLAY2_BUTTON_WIDTH, PLAY2_BUTTON_HEIGHT);

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
            batch.draw(exitButtonInactive, x, y, PLAY1_BUTTON_WIDTH, PLAY1_BUTTON_HEIGHT);
            exitButtonPreviouslyTouched = false;
        }
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
