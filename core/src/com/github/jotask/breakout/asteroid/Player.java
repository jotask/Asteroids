package com.github.jotask.breakout.asteroid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Player
 *
 * @author Jose Vives Iznardo
 * @since 27/07/2017
 */
public class Player {

    public final float SCALE = 10f;
    public final float ROTATION = 5f;
    public final float SPEED = 7f;

    private final Vector2 pos;

    private final Polygon polygon;

    private float angle = 0f;

    public Player() {

        this.pos = new Vector2(100, 100);

        float[] vertices = new float[] {
                -1, -1,
                2, 0f,
                -1, 1
        };

        this.polygon = new Polygon(vertices);
        this.polygon.setPosition(pos.x, pos.y);
        this.polygon.setScale(SCALE, SCALE);

    }

    public void update()
    {

        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            polygon.rotate(ROTATION);
            angle += ROTATION;
        }else if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            polygon.rotate(-ROTATION);
            angle -= ROTATION;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            pos.x += this.SPEED * Math.cos(angle * Math.PI / 180);
            pos.y += this.SPEED * Math.sin(angle * Math.PI / 180);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            pos.x -= this.SPEED * Math.cos(angle * Math.PI / 180);
            pos.y -= this.SPEED * Math.sin(angle * Math.PI / 180);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
        {
            Asteroids.get().getBullets().add(new Bullet(this));
        }

        final Rectangle bounds = polygon.getBoundingRectangle();
        if(bounds.getX() + bounds.getWidth() < 0f)
        {
            pos.x = Asteroids.get().getCamera().viewportWidth;
        }
        else if( bounds.getX() > Asteroids.get().getCamera().viewportWidth )
        {
            pos.x = 0f + bounds.getWidth() * .01f;
        }

        if(bounds.getY() + bounds.getHeight() < 0)
        {
            pos.y = Asteroids.get().getCamera().viewportHeight;
        }
        else if( bounds.getY() > Asteroids.get().getCamera().viewportHeight )
        {
            pos.y = 0f + bounds.getHeight() * .01f;
        }

        polygon.setPosition(pos.x, pos.y);

    }

    public void render(final ShapeRenderer sr)
    {
        sr.polygon(polygon.getTransformedVertices());
    }

    public float getAngle() { return angle; }

    public Vector2 getPosition() { return pos; }

}
