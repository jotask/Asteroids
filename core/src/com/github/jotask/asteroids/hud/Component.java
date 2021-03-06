package com.github.jotask.asteroids.hud;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Component
 *
 * @author Jose Vives Iznardo
 * @since 07/08/2017
 */
public abstract class Component {

    protected final Hud hud;

    public Component(Hud hud) {
        this.hud = hud;
    }

    public abstract void update();

    public abstract void resize(final OrthographicCamera camera);

    public abstract void render(final ShapeRenderer sr);

    public abstract void renderFont(final SpriteBatch sb, final BitmapFont fnt);

}
