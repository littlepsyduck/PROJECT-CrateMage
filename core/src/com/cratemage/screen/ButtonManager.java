package com.cratemage.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.cratemage.CrateMage;

public class ButtonManager {
    private Skin homeskin;
    private Skin openmusicskin;
    private Skin closemusicskin;
    private CrateMage game;

    public ButtonManager(CrateMage game) {
        this.game = game;
        this.homeskin = new Skin(Gdx.files.internal("button/homebutton.json"));
        this.openmusicskin = new Skin(Gdx.files.internal("button/openmusicbutton.json"));
        this.closemusicskin = new Skin(Gdx.files.internal("button/closemusicbutton.json"));
    }

    public Button createHomeButton() {
        Button homebutton = new Button(homeskin);
        homebutton.setSize(50, 50);
        homebutton.setPosition(1190, 0);

        homebutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen(game));
            }
        });

        return homebutton;
    }

    public Button createMusicButton() {
        Button musicButton = new Button(openmusicskin);
        musicButton.setSize(50, 50);
        musicButton.setPosition(1190, 650);

        musicButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                toggleMusic(musicButton);
            }
        });

        return musicButton;
    }

    private void toggleMusic(Button musicButton) {
        boolean isMusicPlaying = game.isSoundOn();
        game.setSoundOn(!isMusicPlaying);

        if (isMusicPlaying) {
            if (game.getScreen() instanceof LevelSelectScreen || game.getScreen() instanceof MenuScreen) {
                game.stopBackgroundMusic();
            } else if (game.getScreen() instanceof GameScreen) {
                game.stopMainMusic();
            }
            musicButton.setStyle(closemusicskin.get(Button.ButtonStyle.class));
        } else {
            if (game.getScreen() instanceof LevelSelectScreen || game.getScreen() instanceof MenuScreen) {
                game.playBackgroundMusic();
            } else if (game.getScreen() instanceof GameScreen) {
                game.playMainMusic();
            }
            musicButton.setStyle(openmusicskin.get(Button.ButtonStyle.class));
        }
    }
}
