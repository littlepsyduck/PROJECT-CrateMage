package com.creatmage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.creatmage.screen.GameScreen;
import com.creatmage.screen.MenuScreen;

public class MyGdxGame extends Game {
	public SpriteBatch batch;
	//public Screen GameScreen;

	@Override
	public void create () {
//		GameScreen = new GameScreen();
//		setScreen(GameScreen);
		batch = new SpriteBatch();

		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}
}
