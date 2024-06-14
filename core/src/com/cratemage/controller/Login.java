package com.cratemage.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.cratemage.CrateMage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Login implements Screen {

    SpriteBatch batch;
    CrateMage game;
    Stage stage;

    public Login(CrateMage game) {
        this.game = game;
        stage = new Stage();
        batch = new SpriteBatch();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        ScreenUtils.clear(1, 1, 1, 0);

        batch.begin();
        Texture background = new Texture("Login/backgr.png");
        Texture login = new Texture("Login/login.png");
        batch.draw(background, 0, 0, 1280, 720);
        batch.draw(login, 310, 150);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
    }
}
