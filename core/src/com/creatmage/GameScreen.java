package com.creatmage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    Texture character;
    float x;
    float y;
    MyGdxGame game;
    public GameScreen(MyGdxGame game) {
        this.game = game;
    }
    @Override
    public void show() {
        character = new Texture("character.gif");
    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            y = y + 4;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            y = y - 4;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x = x + 4;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x = x - 4;
        }

        ScreenUtils.clear(135/255f, 206/255f, 235/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(character, x, y, 100, 100);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
