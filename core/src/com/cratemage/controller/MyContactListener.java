package com.cratemage.controller;

import com.badlogic.gdx.physics.box2d.*;
import com.cratemage.CrateMage;
import com.cratemage.model.Box;
import com.cratemage.model.Goal;
import com.cratemage.model.Player;
import com.cratemage.screen.LevelCompletedScreen;

import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;
import java.util.List;

public class MyContactListener implements ContactListener {
    public ArrayList<Box> boxes;
    public Goal goal;
    public World world;
    public Player player;
    public CrateMage game;
    public boolean completed = false;

    public MyContactListener(CrateMage game, World world, ArrayList<Box> boxes, Goal goal, Player player) {
        this.boxes = boxes;
        this.world = world;
        this.goal = goal;
        this.player = player;
        this.game = game;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        for(Box box : boxes){
            if(fixtureA.getBody() == box.body && fixtureB.getBody() == box.body) return;
            if(fixtureA.getBody() == box.body || fixtureB.getBody() == box.body){
                if(box.isChoose()){
                    for(Box box1: boxes){
                        if(box1.isChoose() && box1 != box){
                            box.checkInput2(box1);
                        }
                    }
                }
                else box.checkInput();
                return;
            }
        }

        if((fixtureA.getBody() == goal.body && fixtureB.getBody() == player.body) || (fixtureB.getBody() == goal.body && fixtureA.getBody() == player.body)){
            completed = true;
            return;
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        for(Box box : boxes){
            if(fixtureA.getBody() == box.body || fixtureB.getBody() == box.body){
                if(box.isChoose()){
                    for(Box box1: boxes){
                        if(box1.isChoose() && box1 != box){
                            box1.body.setLinearVelocity(0, 0);
                            box.body.setLinearVelocity(0, 0);
                        }
                    }
                }
                else box.body.setLinearVelocity(0, 0);
                return;
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
