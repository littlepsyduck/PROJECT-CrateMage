package com.cratemage.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cratemage.CrateMage;
import com.cratemage.controller.ButtonManager;

public class LevelCompletedScreen implements Screen {
    public CrateMage game;
    Stage stage;
    SpriteBatch batch;
    Image image;
    Table table;
    ButtonManager buttonManager;
    Skin next, reset;
    public Label timeLabel, getTime;
    Texture levelComplete;

    public LevelCompletedScreen(CrateMage game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        batch = (SpriteBatch) stage.getBatch();

        buttonManager = new ButtonManager(game);
        Button home = buttonManager.createHomeButton();
        Button menu = buttonManager.createMenuButton();
        Button sound = buttonManager.createMusicButton();

        levelComplete = new Texture("PassLevel/levelComplete.png");

        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        getTime = new Label(String.format("%03d", game.time), new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        table = new Table();
        table.setFillParent(true);

       // image = new Image(new Texture("PassLevel/levelComplete.png"));

        next = new Skin(Gdx.files.internal("PassLevel/nextButton.json"));
        reset = new Skin(Gdx.files.internal("PassLevel/replayButton.json"));

        Button nextButton = new Button(next);
        Button replay = new Button(reset);
        replay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });

        table.add(image).size(590, 390).center().colspan(2).padBottom(20);
        table.row();
        table.add(timeLabel).center().padRight(10);
        table.add(getTime).center().padLeft(10);
        table.row();
        table.add(replay).pad(50);
        table.add(nextButton).pad(50);

        stage.addActor(table);
        stage.addActor(home);
        stage.addActor(sound);
        stage.addActor(menu);

        nextButton.setPosition(330, 160);
        replay.setPosition(560, 160);

        stage.addActor(nextButton);
        stage.addActor(replay);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        ScreenUtils.clear(1, 1, 1, 0);

        batch.begin();
        Texture background = new Texture("PassLevel/backgrendgame.png");
        batch.draw(background, 0, 0, 1280, 720);
        int width=800;
        int height=530;
        batch.draw(levelComplete,(1280-width)/2,(720-height)/2,width,height);

        batch.end();

        stage.act(delta);
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
        stage.dispose();
    }
}
