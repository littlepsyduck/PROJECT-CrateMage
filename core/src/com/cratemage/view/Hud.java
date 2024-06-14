package com.cratemage.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cratemage.common.constant.GameConstant;

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private Integer worldTimer;
    private boolean timeUp;
    private float timeCount;
    private Label countTimeLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;

    public Hud(SpriteBatch batch) {
        worldTimer = 0;
        timeCount = 0;

        viewport = new FitViewport(GameConstant.WINDOW_WIDTH, GameConstant.WINDOW_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        BitmapFont customFont = new BitmapFont(Gdx.files.internal("Map/myfont.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(customFont, Color.WHITE);

        countTimeLabel = new Label(String.format("%03d", worldTimer), labelStyle);
        timeLabel = new Label("TIME", labelStyle);
        levelLabel = new Label("1", labelStyle);
        worldLabel = new Label("LEVEL", labelStyle);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);

        table.row();
        table.add(levelLabel).expandX();
        table.add(countTimeLabel).expandX();

        stage.addActor(table);
    }

    public void update(float dt) {
        timeCount += dt;
        if (timeCount >= 1) {
            worldTimer++;
            countTimeLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    public int getTime() {
        return worldTimer;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
