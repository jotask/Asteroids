package com.github.jotask.breakout.asteroid;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Bullet
 *
 * @author Jose Vives Iznardo
 * @since 27/07/2017
 */
public class Bullet {

    public static final float SPEED = 25f;

    static final float[] vertices = {
            0, 0,
            1, 0,
            0, 1,
            1, 0,
            1, 1,
            0, 1
    };

    private Polygon polygon;
    private float angle;

    public Bullet(final Player player) {
        this.polygon = new Polygon(vertices);
        final Vector2 p = player.getPosition();
        this.polygon.setPosition(p.x, p.y);
        polygon.setScale(25, 5);
        this.angle = player.getAngle();
        polygon.setRotation(this.angle);
    }

    public void update(){
        final Vector2 p = new Vector2(polygon.getX(), polygon.getY());
        p.x += this.SPEED * Math.cos(angle * Math.PI / 180);
        p.y += this.SPEED * Math.sin(angle * Math.PI / 180);
        polygon.setPosition(p.x, p.y);
    }

    public boolean outsideBounds(final Rectangle bounds)
    {
        return !bounds.contains(this.polygon.getBoundingRectangle());
    }

    public boolean collides(final Asteroid asteroid)
    {
        Rectangle a = this.getPolygon().getBoundingRectangle();
        Rectangle b = asteroid.getPolygon().getBoundingRectangle();
        if(a.overlaps(b))
        {
            return Intersector.overlapConvexPolygons(this.getPolygon(), asteroid.getPolygon());
        }
        return false;
    }

    public void render(final ShapeRenderer sr){
        sr.polygon(polygon.getTransformedVertices());
    }

    public Polygon getPolygon() { return polygon; }

}
