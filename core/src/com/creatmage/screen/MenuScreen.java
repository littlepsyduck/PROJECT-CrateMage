package com.creatmage.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.creatmage.MyGdxGame;

public class MenuScreen implements Screen {

	private static final int PLAY1_BUTTON_WIDTH = 200;
	private static final int PLAY1_BUTTON_HEIGHT = 200;
	private static final int PLAY2_BUTTON_WIDTH = 200;
	private static final int PLAY2_BUTTON_HEIGHT = 200;
	private static final int EXIT_BUTTON_WIDTH = 100;
	private static final int EXIT_BUTTON_HEIGHT = 100;


	MyGdxGame game;

	Texture exitButton;
	Texture playButtonInactive;
	Texture playButtonActive;
	Texture exitButtonExit;


	public MenuScreen(MyGdxGame game){
		this.game = game;
		playButtonActive = new Texture("play2.png");
		playButtonInactive = new Texture("play1.png");
		exitButton = new Texture("pngtree-exit-close-button-for-game-user-interface-icon-png-image_4520638.png");
		exitButtonExit = new Texture("DinoSprites_vita.gif");
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float v) {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		ScreenUtils.clear(1, 1, 1, 0);

		game.batch.begin();

		int x = 292 - PLAY1_BUTTON_WIDTH / 2;
		int y = 272 - PLAY1_BUTTON_HEIGHT / 2;
		if (Gdx.input.getX() < x + PLAY1_BUTTON_WIDTH && Gdx.input.getX() > x && Gdx.input.getY() < y + PLAY1_BUTTON_HEIGHT / 3 * 2 && Gdx.input.getY() > y) {
			game.batch.draw(playButtonActive, 272 - PLAY2_BUTTON_WIDTH / 2, 240 - PLAY2_BUTTON_HEIGHT / 2, PLAY2_BUTTON_WIDTH, PLAY2_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				this.dispose();
				game.setScreen(new GameScreen(game));
			}
		} else {
			game.batch.draw(playButtonInactive, 272 - PLAY1_BUTTON_WIDTH / 2, 240 - PLAY1_BUTTON_HEIGHT / 2, PLAY1_BUTTON_WIDTH, PLAY1_BUTTON_HEIGHT);
		}


		x= 292 - EXIT_BUTTON_WIDTH  / 2;
		y = 272 - EXIT_BUTTON_HEIGHT / 2;

		if(Gdx.input.getX()<x + EXIT_BUTTON_WIDTH && Gdx.input.getX()> x && Gdx.input.getY()<y + EXIT_BUTTON_HEIGHT*2 && Gdx.input.getY()> y+ EXIT_BUTTON_HEIGHT ){
			game.batch.draw(exitButton, 272 - EXIT_BUTTON_WIDTH / 2, 240 - EXIT_BUTTON_HEIGHT * 2, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				Gdx.app.exit();
			}
		} else {
			game.batch.draw(exitButtonExit, 272 - EXIT_BUTTON_WIDTH / 2, 240 - EXIT_BUTTON_HEIGHT * 2, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
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
		playButtonActive.dispose();
		playButtonInactive.dispose();
		exitButton.dispose();
	}
}
