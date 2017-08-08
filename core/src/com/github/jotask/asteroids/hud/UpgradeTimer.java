package com.github.jotask.asteroids.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * UpgradeTimer
 *
 * @author Jose Vives Iznardo
 * @since 07/08/2017
 */
public class UpgradeTimer extends Component {

    private final Rectangle bounds;

    public UpgradeTimer(Hud hud) {
        super(hud);
        this.bounds = new Rectangle(0, 0, hud.getCamera().viewportWidth, hud.getCamera().viewportHeight);
    }

    @Override
    public void update() {

    }

    @Override
    public void resize(OrthographicCamera camera) {

    }

    @Override
    public void render(final ShapeRenderer sr) {
        sr.set(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.BROWN);
        sr.rect(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.height);

    }

    @Override
    public void renderFont(SpriteBatch sb, BitmapFont fnt) {

    }

}
