package com.creatmage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MenuScreen implements Screen {
    TextureRegion background;
    Texture playGame;
    Texture getPlayGame;
    Texture exitGame;
    Texture getExitGame;
    MyGdxGame game;
    public MenuScreen(MyGdxGame game) {
        this.game = game;
        background = new TextureRegion(new Texture("background.png"));
        playGame = new Texture("play.png");
        getPlayGame = new Texture("playActive.png");
        exitGame = new Texture("exit.png");
        getExitGame = new Texture("exitActive.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(background, 0, 0);

        if(Gdx.input.getX() <= 350 && Gdx.input.getX() >= 250 && Gdx.input.getY() >= 200 && Gdx.input.getY() <= 300){
            game.batch.draw(getPlayGame, 250, 200, 100, 100);
            if(Gdx.input.isTouched()) game.setScreen(new GameScreen(game));
        }
        else{
            game.batch.draw(playGame, 250, 200, 100, 100);
        }
        game.batch.draw(exitGame, 250, 0, 100, 100);

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
