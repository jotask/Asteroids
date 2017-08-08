package com.github.jotask.asteroids.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.github.jotask.asteroids.Asteroids;

import java.util.LinkedList;

/**
 * Hud
 *
 * @author Jose Vives Iznardo
 * @since 31/07/2017
 */
public class Hud {

    private final SpriteBatch sb;
    private final BitmapFont font;

    public static final float OFFSET = 10f;

    private final Asteroids asteroids;

    private final LinkedList<Component> components;

    private final OrthographicCamera camera;
    private final FillViewport viewport;

    public Hud() {

        this.sb = new SpriteBatch();
        this.font = new BitmapFont();

        this.asteroids = Asteroids.get();
        this.camera = new OrthographicCamera();
        this.viewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), this.camera);
        this.viewport.apply();

        this.camera.position.set(this.camera.viewportWidth * .5f, this.camera.viewportHeight * .5f, 0f);
        this.camera.update();

        this.components = new LinkedList<Component>();
        this.components.add(new Health(this));
        this.components.add(new UpgradeTimer(this));

    }

    public void resize(float width, float height)
    {
        viewport.update((int)width,(int)height);
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
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
        sr.begin();
        sr.setProjectionMatrix(this.camera.combined);
        for(final Component c: components)
        {
            c.render(sr);
        }
        sr.end();

        this.sb.begin();
        this.sb.setProjectionMatrix(this.camera.combined);
        for(final Component c: components)
        {
            c.renderFont(this.sb, this.font);
        }
        this.sb.end();

    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public LinkedList<Component> getComponents(){
        return this.components;
    }

    public void dispose(){
        this.font.dispose();
    }

}
