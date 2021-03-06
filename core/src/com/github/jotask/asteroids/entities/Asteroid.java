package com.github.jotask.asteroids.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.asteroids.Asteroids;
import com.github.jotask.asteroids.utils.Utils;

import java.util.LinkedList;

/**
 * Asteroid
 *
 * @author Jose Vives Iznardo
 * @since 27/07/2017
 */
public class Asteroid extends Entity{

    private static final float[] vertices = {
            -.13f, .1f, // 0
            0f, 1f, // 1
            0f, 1.5f, // 2
            1.7f, 1.7f, // 3
            1.75f, .75f, // 4
            .9f, 0f // 5
    };

    public enum Type {

        BIG ( 64), MEDIUM (32), SMALL(16);

        final int size;

        Type(final int size)
        {
            this.size = size;
        }

    }

    private final float SPEED = 3f;

    private final Type type;

    private final Vector2 vel;

    public Asteroid() {
        this(Type.BIG, MathUtils.random(0, Asteroids.get().getCamera().viewportWidth),  MathUtils.random(0, Asteroids.get().getCamera().viewportHeight));
    }

    public Asteroid(final Asteroid parent) {
        this(Type.values()[(parent.type.ordinal() + 1)], parent.getPolygon().getX(), parent.getPolygon().getY());
    }

    public Asteroid(final Type type, float x, float y) {
        super(vertices);
        this.type = type;

        final Polygon polygon = this.getPolygon();
        polygon.setPosition(x, y);
        polygon.setScale(this.type.size, this.type.size);

        this.vel = new Vector2();

        this.vel.add(Utils.getRandomVelocity().scl(SPEED));

        polygon.rotate(MathUtils.random(0, 360));


    }

    public void update()
    {
        final Vector2 pos = new Vector2(this.getPolygon().getX(), this.getPolygon().getY());

        final Rectangle bounds = this.getPolygon().getBoundingRectangle();
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

        pos.add(vel);
        this.getPolygon().setPosition(pos.x, pos.y);

    }

    public void render(final ShapeRenderer sr)
    {
        sr.setColor(Color.WHITE);
        sr.polygon(this.getPolygon().getTransformedVertices());
    }

    public LinkedList<Asteroid> mutate()
    {

        final LinkedList<Asteroid> childs = new LinkedList<Asteroid>();

        if(this.type == Type.SMALL)
            return childs;

        for(int i = 0; i < 2; i++)
        {
            childs.add(new Asteroid(this));
        }

        return childs;
    }

    public Type getType() {
        return type;
    }

}
