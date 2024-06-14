package com.cratemage.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cratemage.CrateMage;
import com.cratemage.controller.ButtonManager;

public class LevelSelectScreen extends ApplicationAdapter implements Screen {
    Stage stage;
    Skin skin;
    SpriteBatch batch;
    Texture backgr;
    CrateMage game;
    private Music clickSound;


    public LevelSelectScreen(CrateMage game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        batch = (SpriteBatch) stage.getBatch();

        Table table = new Table();
        table.setFillParent(true);
        backgr = new Texture("levelSelect/backgr.png");

        skin = new Skin(Gdx.files.internal("levelSelect/level.json"));


        ButtonManager buttonManager = new ButtonManager(game);
        Button homeButton = buttonManager.createHomeButton();
        Button musicButton = buttonManager.createMusicButton();
        stage.addActor(homeButton);
        stage.addActor(musicButton);

        Button[] buttons = new Button[11];

        for(int i = 1; i <= 10; i++){
            String styleName = "level" + i;
            buttons[i] = new Button(skin, styleName);

            int finalI = i;
            buttons[i].addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (game.isSoundOn()) {
                        clickSound.play();
                    }
                    game.levelCurrent = finalI;
                    game.setScreen(new GameScreen(game));
                }
            });
        }
        // Disable buttons for levels that are not available
        for (int i = game.levelUnlocked + 1; i <= 10; i++) {
            buttons[i].setDisabled(true);
        }

        for (int i = 1; i <= 5; i++) {
            table.add(buttons[i]).pad(50);
        }
        table.row();
        for (int i = 6; i <= 10; i++) {
            table.add(buttons[i]).pad(50);
        }

        stage.addActor(table);

        //---sound button

        clickSound = Gdx.audio.newMusic(Gdx.files.internal("Sound/MouseClick.mp3"));
    }

    @Override
    public void show() {
        game.stopAllMusic();
        game.playBackgroundMusic();
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(backgr, 0, 0, 1280, 720);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        game.stopBackgroundMusic();
    }

    @Override
    public void dispose() {
        stage.dispose();
        backgr.dispose();
    }
}
