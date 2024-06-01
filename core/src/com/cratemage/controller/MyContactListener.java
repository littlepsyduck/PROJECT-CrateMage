package com.cratemage.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.cratemage.model.Box;
import com.cratemage.model.Player;

import java.util.ArrayList;

public class MyContactListener implements ContactListener {
    public ArrayList<Box> boxes;
    World world;

    public MyContactListener(ArrayList<Box> boxes, World world) {
        this.boxes = boxes;
        this.world = world;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        for(Box box : boxes){
            if(fixtureA.getBody() == box.body || fixtureB.getBody() == box.body){
                //System.out.println("has hit");
                box.checkInput();
                break;
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        for(Box box : boxes){
            if(fixtureA.getBody() == box.body || fixtureB.getBody() == box.body){
                box.body.setLinearVelocity(0, 0);
                break;
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
