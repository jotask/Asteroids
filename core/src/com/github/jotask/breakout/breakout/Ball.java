package com.github.jotask.breakout.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Ball
 *
 * @author Jose Vives Iznardo
 * @since 27/07/2017
 */
public class Ball {

    private final float radius = 50f;

    private Vector2 pos;
    private Vector2 vel;

    public Ball() {
        pos = new Vector2(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        vel = new Vector2(10f, 10f);
    }

    public void update(){

        if((pos.x < radius) || (pos.x > Gdx.graphics.getWidth() - radius))
        {
            vel.x *= -1;
        }

        if((pos.y < radius) || (pos.y > Gdx.graphics.getHeight() - radius))
        {
            vel.y *= -1;
        }

        pos.add(vel);

    }

    public void render(final SpriteBatch sb)
    {

    }

    public void debug(final ShapeRenderer sr)
    {
        sr.setColor(Color.WHITE);
        sr.circle(pos.x, pos.y, radius);
    }


    public boolean collide(final Rectangle rect)
    {
        // (pos.x < radius) || (pos.x > Gdx.graphics.getWidth() - radius)
        if (rect.x < getPos().x + getRadius() && (rect.x + rect.width) > getPos().x - getRadius())
        {
            if (rect.y < getPos().y + getRadius() && (rect.y + rect.height) > getPos().y - getRadius())
            {
                // Collides with one block
                // Know which side of the block collides
                float x = (rect.x + (rect.width / 2f)) - (pos.x + radius);
                float y = (rect.y + (rect.height /2f)) - (pos.y + radius);

                System.out.println(x + " : " + y);

                if (Math.abs(x) > Math.abs(y))
                {
                    // reflect horizontally
                    vel.x *= -1;
                }
                else
                {
                    // reflect vertically
                    vel.y *= -1;
                }

                final Vector2 center = rect.getCenter(new Vector2());
                final Vector2 dff = new Vector2();


                return true;
            }
        }// (pos.y < radius) || (pos.y > Gdx.graphics.getHeight() - radius)

        return false;
    }

    public float getRadius() {
        return radius;
    }

    public Vector2 getPos() {
        return pos;
    }

    public Vector2 getVel() {
        return vel;
    }
}
