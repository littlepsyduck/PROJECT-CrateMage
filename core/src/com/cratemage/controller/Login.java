package com.cratemage.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.cratemage.CrateMage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;
import com.cratemage.screen.LevelSelectScreen;

public class Login implements Screen {

    SpriteBatch batch;
    CrateMage game;
    Stage stage;
    private Skin submitskin;
    private Music clickSound;

    public Login(CrateMage game) {
        this.game = game;
        stage = new Stage();
        batch = new SpriteBatch();
        this.submitskin = new Skin(Gdx.files.internal("Login/submit.json"));
        this.clickSound = Gdx.audio.newMusic(Gdx.files.internal("Sound/MouseClick.mp3")); // Đảm bảo âm thanh được khởi tạo

        Button submitButton = createSubmitButton();
        stage.addActor(submitButton); // Thêm button vào stage
    }

    public Button createSubmitButton() {
        Button submitButton = new Button(submitskin);
        submitButton.setSize(230, 80);
        submitButton.setPosition(520, 225);

        submitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (game.isSoundOn()) {
                    clickSound.play();
                }
                game.setScreen(new LevelSelectScreen(game));
            }
        });

        return submitButton;
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
