package com.cratemage.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.cratemage.screen.GameScreen;

import static com.cratemage.common.constant.GameConstant.PPM;

public class Box extends Sprite {
    public World world;
    public Body body;
    public Texture texture;
    public float speed;
    boolean contact = false;
    public Box(GameScreen screen, Body body) {
        this.world = screen.world;
        texture = new Texture("Map/box.png");
        setRegion(texture);
        setBounds(body.getPosition().x,body.getPosition().y,20/PPM, 20/PPM);
        this.body = body;
        this.speed = 5f;
        body.setUserData(this);
    }
    public void update(float dt){
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
    }

    public void checkInput() {
        float velX = 0, velY = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) velY = 1;
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) velY = -1;
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) velX = -1;
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) velX = 1;
        body.setLinearVelocity(velX, velY);
    }
}