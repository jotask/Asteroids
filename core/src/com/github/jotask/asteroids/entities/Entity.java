package com.github.jotask.asteroids.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;

/**
 * Entity
 *
 * @author Jose Vives Iznardo
 * @since 07/08/2017
 */
public abstract class Entity {

    private final Polygon polygon;

    public Entity(final float[] vertices){
        this.polygon = new Polygon(vertices);
    }

    public boolean collides(final Entity other)
    {
        if(this.polygon.getBoundingRectangle().overlaps(other.getPolygon().getBoundingRectangle())){
            return Intersector.overlapConvexPolygons(this.polygon, other.getPolygon());
        }
        return false;
    }

    public abstract void update();

    public abstract void render(final ShapeRenderer sr);

    public Polygon getPolygon() {
        return polygon;
    }
}
