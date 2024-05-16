package com.cratemage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cratemage.screen.MenuScreen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class CrateMage extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public OrthographicCamera camera;
	//public Screen GameScreen;

	@Override
	public void create () {
//		GameScreen = new GameScreen();
//		setScreen(GameScreen);
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}
}
