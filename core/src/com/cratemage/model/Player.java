package com.cratemage.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.cratemage.screen.GameScreen;

import static com.cratemage.common.constant.GameConstant.PPM;

public class Player extends Sprite {
    public enum State {IDLE, UP, DOWN, LEFT, RIGHT, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT}

    public State currentState;
    public State previousState;

    public Texture playerTexture;
    public Body body;
    public World world;
    public Animation[] up;
    public Animation[] down;
    public Animation[] left;
    public Animation[] right;
    public Animation[] stand;
    public float speed, velX, velY, stateTimer;


    public Player(GameScreen gameScreen, Body body) {
        this.world = gameScreen.world;
        currentState = State.IDLE;
        previousState = State.IDLE;
        stateTimer = 0;
        this.speed = 10f;
        this.body = body;
        setBounds(body.getPosition().x, body.getPosition().y, 48 / PPM, 48 / PPM);


        up = new Animation[1];
        down = new Animation[1];
        left = new Animation[1];
        right = new Animation[1];
        stand = new Animation[1];

        playerTexture = new Texture("character/sheets/DinoSprites - tard.png");
        TextureRegion[][] playerTextureRegion = TextureRegion.split(playerTexture, 24, 24);
        TextureRegion[] rollFrames = new TextureRegion[24 * 1];
        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 24; j++) {
                rollFrames[index++] = playerTextureRegion[i][j];
            }
        }
        down[0] = new Animation(0.2f, rollFrames[4], rollFrames[5], rollFrames[6], rollFrames[7], rollFrames[8], rollFrames[9]);
        up[0] = new Animation(0.2f, rollFrames[4], rollFrames[5], rollFrames[6], rollFrames[7], rollFrames[8], rollFrames[9]);
        left[0] = new Animation(0.2f, rollFrames[4], rollFrames[5], rollFrames[6], rollFrames[7], rollFrames[8], rollFrames[9]);
        right[0] = new Animation(0.2f, rollFrames[4], rollFrames[5], rollFrames[6], rollFrames[7], rollFrames[8], rollFrames[9]);
        stand[0] = new Animation(0.2f, rollFrames[0], rollFrames[1], rollFrames[2], rollFrames[3]);
        up[0].setPlayMode(Animation.PlayMode.LOOP);
        down[0].setPlayMode(Animation.PlayMode.LOOP);
        left[0].setPlayMode(Animation.PlayMode.LOOP);
        right[0].setPlayMode(Animation.PlayMode.LOOP);
        stand[0].setPlayMode(Animation.PlayMode.LOOP);
    }

    public void update(float dt) {
        checkUserInput();
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight()/2.5f);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        TextureRegion region;
        switch (currentState) {
            case UP:
                region = (TextureRegion) up[0].getKeyFrame(stateTimer, true);
                break;
            case DOWN:
                region = (TextureRegion) down[0].getKeyFrame(stateTimer, true);
                break;
            case LEFT:
            case UPLEFT:
            case DOWNLEFT:
                region = (TextureRegion) left[0].getKeyFrame(stateTimer, true);
                break;
            case RIGHT:
            case DOWNRIGHT:
            case UPRIGHT:
                region = (TextureRegion) right[0].getKeyFrame(stateTimer, true);
                break;
            default:
                region = (TextureRegion) stand[0].getKeyFrame(stateTimer, true);
                break;
        }
        stateTimer += dt;
        return region;
    }

    public void checkUserInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            currentState = State.UP;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            currentState = State.DOWN;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            currentState = State.LEFT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            currentState = State.RIGHT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)) {
            currentState = State.UPLEFT;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)) {
            currentState = State.UPRIGHT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.S)) {
            currentState = State.DOWNLEFT;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.S)) {
            currentState = State.DOWNRIGHT;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)) {
            currentState = State.IDLE;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            System.out.println("X: " + body.getPosition().x + " Y: " + body.getPosition().y);
        }

        velX = 0;
        velY = 0;
        if (currentState == State.UP) {
            velY = 0.5f;
        } else if (currentState == State.DOWN) {
            velY = -0.5f;
        } else if (currentState == State.LEFT) {
            velX = -0.5f;
        } else if (currentState == State.RIGHT) {
            velX = 0.5f;
        } else if (currentState == State.UPLEFT) {
            velX = -0.35f;
            velY = 0.35f;
        } else if (currentState == State.UPRIGHT) {
            velX = 0.35f;
            velY = 0.35f;
        } else if (currentState == State.DOWNLEFT) {
            velX = -0.35f;
            velY = -0.35f;
        } else if (currentState == State.DOWNRIGHT) {
            velX = 0.35f;
            velY = -0.35f;
        }
        body.setLinearVelocity(velX * speed, velY * speed);


    }
}