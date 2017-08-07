package com.github.jotask.breakout.asteroid.hud;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.breakout.asteroid.Asteroids;

import java.util.LinkedList;

/**
 * Hud
 *
 * @author Jose Vives Iznardo
 * @since 31/07/2017
 */
public class Hud {

    public static final float OFFSET = 10f;

    private final Asteroids asteroids;

    private final LinkedList<Component> components;

    private final OrthographicCamera camera;

    public Hud() {
        this.asteroids = Asteroids.get();
        this.camera = new OrthographicCamera();

        this.components = new LinkedList<Component>();
        this.components.add(new Health(this));

    }

    public void resize(float width, float height)
    {
        this.camera.setToOrtho(true, width, height);
        this.camera.update();
    }

    public void update()
    {
        for(final Component c: components)
        {
            c.update();
        }
    }

    public void render(final ShapeRenderer sr)
    {
        sr.setProjectionMatrix(this.camera.combined);
        for(final Component c: components)
        {
            c.render(sr);
        }
    }

}
