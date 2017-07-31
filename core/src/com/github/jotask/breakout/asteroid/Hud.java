package com.github.jotask.breakout.asteroid;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Hud
 *
 * @author Jose Vives Iznardo
 * @since 31/07/2017
 */
public class Hud {

    private final Asteroids asteroids;

    private final OrthographicCamera camera;

    public Hud() {
        this.asteroids = Asteroids.get();
        this.camera = new OrthographicCamera();
    }

    public void resize(float width, float height)
    {
        this.camera.setToOrtho(true, width, height);
        this.camera.update();
    }

    public void update()
    {

    }

    public void render(final ShapeRenderer sr)
    {
        sr.setProjectionMatrix(this.camera.combined);
    }

}
