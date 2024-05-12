package com.cratemage.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

public class LevelSelectScreen extends ApplicationAdapter implements Screen {
    Stage stage;
    Skin skin;
    SpriteBatch batch;
    Texture backgr;
    CrateMage game;

    public LevelSelectScreen(CrateMage game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        batch = (SpriteBatch) stage.getBatch();

        Table table = new Table();
        table.setFillParent(true);
        backgr = new Texture("levelSelect/backgr.png");

        skin = new Skin(Gdx.files.internal("levelSelect/level.json"));

        Button[] buttons = new Button[11];
        buttons[1] = new Button(skin, "level1");
        buttons[2] = new Button(skin, "level2");
        buttons[3] = new Button(skin, "level3");
        buttons[4] = new Button(skin, "level4");
        buttons[5] = new Button(skin, "level5");
        buttons[6] = new Button(skin, "level6");
        buttons[7] = new Button(skin, "level7");
        buttons[8] = new Button(skin, "level8");
        buttons[9] = new Button(skin, "level9");
        buttons[10] = new Button(skin, "level10");

        buttons[1].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new DemoGameScreen(game));
            }
        });

        for(int i = 1; i <= 5; i++){
            table.add(buttons[i]).pad(50);
        }
        //table.add(buttons[1], buttons[2], buttons[3], buttons[4], buttons[5]);
        table.row();
        table.add(buttons[6], buttons[7], buttons[8], buttons[9], buttons[10]);
        buttons[3].setDisabled(true); // set disabled
        buttons[4].setDisabled(true);
        buttons[5].setDisabled(true);
        buttons[6].setDisabled(true);
        buttons[7].setDisabled(true);
        buttons[8].setDisabled(true);
        buttons[9].setDisabled(true);
        buttons[10].setDisabled(true);

        stage.addActor(table);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(backgr, 0, 0);
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

    }
}
