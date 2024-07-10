package com.cratemage.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.cratemage.screen.GameScreen;
import jdk.internal.net.http.common.Pair;

import static com.cratemage.common.constant.GameConstant.PPM;

public class Box extends Sprite {
    public World world;
    public Body body;
    public Texture texture;
    public float speed;
    public String filePath;
    private boolean choose;
    public char idx;
    boolean contact = false;
    public Box(GameScreen screen, Body body, char idx) {
        this.world = screen.world;
        this.idx = idx;
        filePath = "Map/box_" + idx + ".png";
        texture = new Texture(filePath);
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

    public void checkInput2(Box box) {
        float velX = 0, velY = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) velY = -1;
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) velY = 1;
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) velX = 1;
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) velX = -1;

        body.setLinearVelocity(velX, velY);
        box.body.setLinearVelocity(velX, velY);
    }

    public boolean isChoose() {
        return choose;
    }

    public void setChoose(boolean choose) {
        this.choose = choose;
        if(isChoose()){
            filePath = "Map/box_" + idx + "_active.png";
            texture = new Texture(filePath);
            setRegion(texture);
        }
        else{
            filePath = "Map/box_" + idx + ".png";
            texture = new Texture(filePath);
            setRegion(texture);
        }
    }
}