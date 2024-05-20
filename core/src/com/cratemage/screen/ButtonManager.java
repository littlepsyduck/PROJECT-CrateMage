package com.cratemage.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.cratemage.CrateMage;

public class ButtonManager {
    private Skin homeskin;
    private Skin menuskin;
    private Skin resetskin;
    private Skin openmusicskin;
    private Skin closemusicskin;
    private CrateMage game;
    private Music clickSound;

    public ButtonManager(CrateMage game) {
        this.game = game;
        this.homeskin = new Skin(Gdx.files.internal("button/homebutton.json"));
        this.menuskin = new Skin(Gdx.files.internal("button/menubutton.json"));
        this.resetskin = new Skin(Gdx.files.internal("button/resetbutton.json"));
        this.openmusicskin = new Skin(Gdx.files.internal("button/openmusicbutton.json"));
        this.closemusicskin = new Skin(Gdx.files.internal("button/closemusicbutton.json"));
        //---sound button

        clickSound = Gdx.audio.newMusic(Gdx.files.internal("Sound/MouseClick.mp3"));
    }

    public Button createHomeButton() {
        Button homebutton = new Button(homeskin);
        homebutton.setSize(50, 50);
        homebutton.setPosition(1150, 650);

        homebutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (game.isSoundOn()) {
                    clickSound.play();
                }
                game.setScreen(new MenuScreen(game));
            }
        });

        return homebutton;
    }

    public Button createMenuButton() {
        Button menubutton = new Button(menuskin);
        menubutton.setSize(50, 50);
        menubutton.setPosition(1090, 650);

        menubutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (game.isSoundOn()) {
                    clickSound.play();
                }
                game.setScreen(new LevelSelectScreen(game));
            }
        });

        return menubutton;
    }

    public Button creatResetButton() {
        Button resetbutton = new Button(resetskin);
        resetbutton.setSize(50, 50);
        resetbutton.setPosition(1030, 650);

        resetbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (game.isSoundOn()) {
                    clickSound.play();
                }
                game.setScreen(new GameScreen(game));
            }
        });

        return resetbutton;
    }

    public Button createMusicButton() {
        Button musicButton = new Button(game.isSoundOn() ? openmusicskin : closemusicskin);
        musicButton.setSize(50, 50);
        musicButton.setPosition(1210, 650);

        musicButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (game.isSoundOn()) {
                    clickSound.play();
                }
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
