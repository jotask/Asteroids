package com.github.jotask.asteroids.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Bullet
 *
 * @author Jose Vives Iznardo
 * @since 27/07/2017
 */
public class Bullet extends Entity{

    public static final float SPEED = 25f;

    static final float[] vertices = {
            0, 0,
            1, 0,
            0, 1,
            1, 0,
            1, 1,
            0, 1
    };

    private float angle;

    public Bullet(final Player player) {
        super(vertices);
        this.angle = player.getAngle();
        final Vector2 p = player.getPosition();
        this.getPolygon().setPosition(p.x, p.y);
        this.getPolygon().setScale(25, 5);
        this.getPolygon().setRotation(this.angle);

        this.angle = player.getAngle();

    }

    public void update(){
        final Vector2 p = new Vector2(this.getPolygon().getX(), this.getPolygon().getY());
        p.x += this.SPEED * Math.cos(angle * Math.PI / 180);
        p.y += this.SPEED * Math.sin(angle * Math.PI / 180);
        this.getPolygon().setPosition(p.x, p.y);
    }

    public boolean outsideBounds(final Rectangle bounds)
    {
        return !bounds.contains(this.getPolygon().getBoundingRectangle());
    }

    public void render(final ShapeRenderer sr){
        sr.polygon(getPolygon().getTransformedVertices());
    }

}
