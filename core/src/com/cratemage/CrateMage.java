package com.cratemage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cratemage.screen.MenuScreen;

public class CrateMage extends Game {
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
