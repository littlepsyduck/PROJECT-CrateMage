package com.cratemage.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.cratemage.screen.GameScreen;

import static com.cratemage.common.constant.GameConstant.PPM;

public class Player extends Sprite {
    public enum State {IDLE, UP, DOWN, LEFT, RIGHT}

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
        this.speed = 2.5f;
        this.body = body;
        body.setGravityScale(0);
        setBounds(body.getPosition().x, body.getPosition().y, 20 / PPM, 20 / PPM);
        up = new Animation[1];
        down = new Animation[1];
        left = new Animation[1];
        right = new Animation[1];
        stand = new Animation[1];

        playerTexture = new Texture("character/Full Spritesheet.png");
        TextureRegion[][] playerTextureRegion = TextureRegion.split(playerTexture, 24, 24);
        TextureRegion[] rollFrames = new TextureRegion[12*4];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                rollFrames[index++] = playerTextureRegion[i][j];
            }
        }
        down[0] = new Animation(0.08f, rollFrames[0], rollFrames[1], rollFrames[2], rollFrames[3], rollFrames[4], rollFrames[5], rollFrames[6], rollFrames[7], rollFrames[8], rollFrames[9], rollFrames[10], rollFrames[11]);
        up[0] = new Animation(0.08f, rollFrames[12], rollFrames[13], rollFrames[14], rollFrames[15], rollFrames[16], rollFrames[17], rollFrames[18], rollFrames[19], rollFrames[20], rollFrames[21], rollFrames[22], rollFrames[23]);
        left[0] = new Animation(0.08f, rollFrames[36], rollFrames[37], rollFrames[38], rollFrames[39], rollFrames[40], rollFrames[41], rollFrames[42], rollFrames[43], rollFrames[44], rollFrames[45], rollFrames[46], rollFrames[47]);
        right[0] = new Animation(0.08f, rollFrames[24], rollFrames[25], rollFrames[26], rollFrames[27], rollFrames[28], rollFrames[29], rollFrames[30], rollFrames[31], rollFrames[32], rollFrames[33], rollFrames[34], rollFrames[35]);
        stand[0] = new Animation(1f, rollFrames[0]);
        up[0].setPlayMode(Animation.PlayMode.LOOP);
        down[0].setPlayMode(Animation.PlayMode.LOOP);
        left[0].setPlayMode(Animation.PlayMode.LOOP);
        right[0].setPlayMode(Animation.PlayMode.LOOP);
        stand[0].setPlayMode(Animation.PlayMode.LOOP);
    }

    public void update(float dt) {
        checkUserInput();
        setPosition(body.getPosition().x - getWidth() / 2f, body.getPosition().y - getHeight() / 2f);
        setRegion(getFrame(dt));
        //System.out.println(body.getPosition().x + " " +  body.getPosition().y);
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
                region = (TextureRegion) left[0].getKeyFrame(stateTimer, true);
                break;
            case RIGHT:
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
        velX = 0;
        velY = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            currentState = State.UP;

            velY = 1f;

        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            currentState = State.DOWN;

            velY = -1f;

        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            currentState = State.LEFT;

            velX = -1f;

        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            currentState = State.RIGHT;
            //body.applyLinearImpulse(new Vector2(speed, 0), body.getWorldCenter(), true);
            velX = 1f;
        }

        else if (!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S) && !Gdx.input.isKeyPressed(Input.Keys.UP) && !Gdx.input.isKeyPressed(Input.Keys.DOWN) && !Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            currentState = State.IDLE;
        }

        body.setLinearVelocity(velX * speed, velY * speed);

    }
}