package com.cratemage.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.World;
import com.cratemage.screen.GameScreen;

import static com.cratemage.common.constant.GameConstant.PPM;

public class Box extends Sprite {
    public World world;
    public Body body;
    public TextureRegion[][] region;

    public Texture texture;
    public float speed, velX, velY;
    public Box(GameScreen screen, Body body) {
        this.world = screen.world;
        texture = new Texture("Map/box.png");
        setRegion(texture);
        setBounds(body.getPosition().x,body.getPosition().y,20/PPM, 20/PPM);
        this.body = body;
//        MassData massData = new MassData();
//        massData.mass = 5;
//        this.body.setMassData(massData);
        this.speed = 5f;

    }

    public void update(float dt){
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        //body.setLinearVelocity(velX * speed, velY * speed);
    }


}
