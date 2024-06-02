package com.cratemage.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.cratemage.screen.GameScreen;

public class Goal {
    public World world;
    public Body body;

    public Goal(GameScreen gameScreen, Body body) {
        world = gameScreen.world;
        this.body = body;
    }
}
