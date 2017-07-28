package com.github.jotask.breakout.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

/**
 * Player
 *
 * @author Jose Vives Iznardo
 * @since 27/07/2017
 */
public class Player extends Break {

    public final float speed = 10f;

    private Vector2 vel;

    public Player() {
        super(0,25,200, 200);
        vel = new Vector2();
    }

    @Override
    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            this.vel.x = 1 * speed;
        }else if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            this.vel.x = -1 * speed;
        }else{
            this.vel.x = 0;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            this.vel.y = 1 * speed;
        }else if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            this.vel.y = -1 * speed;
        }else{
            this.vel.y = 0;
        }
        this.rect.x += vel.x;
        this.rect.y += vel.y;

        if(this.rect.x < 0)
        {
            this.rect.x = 0;
        }
        else if(this.rect.getX() + this.rect.getWidth() > Gdx.graphics.getWidth())
        {
            this.rect.x = Gdx.graphics.getWidth() - this.rect.getWidth();
        }
        if(this.rect.y < 0)
        {
            this.rect.y = 0;
        }
        else if(this.rect.getY() + this.rect.getHeight() > Gdx.graphics.getHeight())
        {
            this.rect.y = Gdx.graphics.getHeight() - this.rect.getHeight();
        }
    }
}
