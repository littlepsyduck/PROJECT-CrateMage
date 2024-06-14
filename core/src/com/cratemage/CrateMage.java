package com.cratemage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cratemage.screen.MenuScreen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class CrateMage extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public int time;
	public OrthographicCamera camera;
	private Music backgroundMusic;
	private Music mainMusic;
	private boolean isSoundOn = true; // Add this line
	public int levelCurrent = 1, levelUnlocked = 1;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/menusound.mp3"));
		mainMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/mainsound.mp3"));

		backgroundMusic.setLooping(true);
		mainMusic.setLooping(true);
		playBackgroundMusic();
		this.setScreen(new MenuScreen(this));
	}

	public void playBackgroundMusic() {
		if (isSoundOn && !backgroundMusic.isPlaying()) {
			backgroundMusic.play();
		}
	}

	public void stopBackgroundMusic() {
		if (backgroundMusic.isPlaying()) {
			backgroundMusic.stop();
		}
	}

	public void playMainMusic() {
		if (isSoundOn && !mainMusic.isPlaying()) {
			mainMusic.play();
		}
	}

	public void stopMainMusic() {
		if (mainMusic.isPlaying()) {
			mainMusic.stop();
		}
	}

	public boolean isSoundOn() {
		return isSoundOn;
	}

	public void setSoundOn(boolean isSoundOn) {
		this.isSoundOn = isSoundOn;
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		backgroundMusic.dispose();
		mainMusic.dispose();
	}
	public void stopAllMusic() {
		stopBackgroundMusic();
		stopMainMusic();

	}
}
