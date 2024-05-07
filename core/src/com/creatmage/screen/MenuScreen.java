package com.creatmage.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.creatmage.MyGdxGame;

public class MenuScreen implements Screen {

    private static final int PLAY1_BUTTON_WIDTH = 200;
    private static final int PLAY1_BUTTON_HEIGHT = 70;
    private static final int PLAY2_BUTTON_WIDTH = 200;
    private static final int PLAY2_BUTTON_HEIGHT = 70;
    private static final int NAME_BUTTON_WIDTH = 300;
    private static final int NAME_BUTTON_HEIGHT = 300;


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

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        ScreenUtils.clear(1, 1, 1, 0);

        game.batch.begin();

        //--- Draw BACKGR ---s
        Texture background = new Texture("backgr.png");
        game.batch.draw(background, 0, 0, 800, 450);
        game.batch.draw(namegame,800-NAME_BUTTON_WIDTH-100,450-NAME_BUTTON_HEIGHT+10,NAME_BUTTON_WIDTH,NAME_BUTTON_HEIGHT);

        //--- NEWGAME

        int x = 200 - PLAY1_BUTTON_WIDTH / 2;
        int y = 450 - PLAY1_BUTTON_HEIGHT * 2;

        if (Gdx.input.getX() < x + PLAY1_BUTTON_WIDTH &&
                Gdx.input.getX() > x &&
                Gdx.input.getY() > Gdx.graphics.getHeight() - (y + PLAY1_BUTTON_HEIGHT) &&
                Gdx.input.getY() < Gdx.graphics.getHeight() - y) {
            game.batch.draw(playButtonActive, 200 - PLAY2_BUTTON_WIDTH / 2, y, PLAY2_BUTTON_WIDTH, PLAY2_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new GameScreen(game));
            }
        } else {
            game.batch.draw(playButtonInactive, 200 - PLAY1_BUTTON_WIDTH / 2, y, PLAY1_BUTTON_WIDTH, PLAY1_BUTTON_HEIGHT);
        }


        //-- CONTINUE

         x = 200 - PLAY1_BUTTON_WIDTH / 2;
         y = 450 - PLAY1_BUTTON_HEIGHT * 2 - 80;

        if (Gdx.input.getX() < x + PLAY1_BUTTON_WIDTH &&
                Gdx.input.getX() > x &&
                Gdx.input.getY() > Gdx.graphics.getHeight() - (y + PLAY1_BUTTON_HEIGHT) &&
                Gdx.input.getY() < Gdx.graphics.getHeight() - y) {
            game.batch.draw(continueButtonActive, 200 - PLAY2_BUTTON_WIDTH / 2, y, PLAY2_BUTTON_WIDTH, PLAY2_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new GameScreen(game));
            }
        } else {
            game.batch.draw(continueButtonInactive, 200 - PLAY1_BUTTON_WIDTH / 2, y, PLAY1_BUTTON_WIDTH, PLAY1_BUTTON_HEIGHT);
        }

        //-- TUTORIAL

        x = 200 - PLAY1_BUTTON_WIDTH / 2;
        y = 450 - PLAY1_BUTTON_HEIGHT * 2 - 160;

        if (Gdx.input.getX() < x + PLAY1_BUTTON_WIDTH &&
                Gdx.input.getX() > x &&
                Gdx.input.getY() > Gdx.graphics.getHeight() - (y + PLAY1_BUTTON_HEIGHT) &&
                Gdx.input.getY() < Gdx.graphics.getHeight() - y) {
            game.batch.draw(tutorialButtonActive, 200 - PLAY2_BUTTON_WIDTH / 2, y, PLAY2_BUTTON_WIDTH, PLAY2_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new GameScreen(game));
            }
        } else {
            game.batch.draw(tutorialButtonInactive, 200 - PLAY1_BUTTON_WIDTH / 2, y, PLAY1_BUTTON_WIDTH, PLAY1_BUTTON_HEIGHT);
        }

        x = 200 - PLAY1_BUTTON_WIDTH / 2;
        y = 450 - PLAY1_BUTTON_HEIGHT * 2 - 240;
        if (Gdx.input.getX() < x + PLAY1_BUTTON_WIDTH &&
                Gdx.input.getX() > x &&
                Gdx.input.getY() > Gdx.graphics.getHeight() - (y + PLAY1_BUTTON_HEIGHT) &&
                Gdx.input.getY() < Gdx.graphics.getHeight() - y) {
            game.batch.draw(exitButtonActive, 200 - PLAY2_BUTTON_WIDTH / 2, y, PLAY2_BUTTON_WIDTH, PLAY2_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
                game.setScreen(new GameScreen(game));
            }
        } else {
            game.batch.draw(exitButtonInactive, 200 - PLAY1_BUTTON_WIDTH / 2, y, PLAY1_BUTTON_WIDTH, PLAY1_BUTTON_HEIGHT);
        }

//
//		if(Gdx.input.getX()<x + EXIT_BUTTON_WIDTH && Gdx.input.getX()> x && Gdx.input.getY()<y + EXIT_BUTTON_HEIGHT*2 && Gdx.input.getY()> y+ EXIT_BUTTON_HEIGHT ){
//			game.batch.draw(exitButton, 500 - EXIT_BUTTON_WIDTH / 2, 240 - EXIT_BUTTON_HEIGHT * 2, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
//			if (Gdx.input.isTouched()) {
//				Gdx.app.exit();
//			}
//		} else {
//			game.batch.draw(exitButtonExit, 272 - EXIT_BUTTON_WIDTH / 2, 240 - EXIT_BUTTON_HEIGHT * 2, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
//		}


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
        playButtonActive.dispose();
       // playButtonInactive.dispose();
        exitButtonActive.dispose();
       // exitButtonInactive.dispose();
    }
}
