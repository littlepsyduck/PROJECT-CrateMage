package com.cratemage.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cratemage.CrateMage;
import com.cratemage.controller.ButtonManager;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class TutorialScreen implements Screen {

    private CrateMage game;
    private SpriteBatch batch;
    private Texture tutorialImage;
    private Stage stage;
    private ButtonManager buttonManager;

    public TutorialScreen(CrateMage game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.tutorialImage = new Texture("tutorial/tutorial.png");
        this.stage = new Stage(new ScreenViewport());
        this.buttonManager = new ButtonManager(game);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Button homeButton = buttonManager.createHomeButton();
        Button musicButton = buttonManager.createMusicButton();
        stage.addActor(homeButton);
        stage.addActor(musicButton);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);

        batch.begin();
        batch.draw(tutorialImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        batch.dispose();
        tutorialImage.dispose();
        stage.dispose();
    }
}