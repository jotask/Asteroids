package com.github.jotask.breakout.breakout;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Break
 *
 * @author Jose Vives Iznardo
 * @since 27/07/2017
 */
public class Break {

    protected Rectangle rect;

    public Break() {
        this(200, 200,45,20);
    }

    public Break(float x, float y, float w, float h) {
        rect = new Rectangle(x, y, w, h);
    }

    public void update()
    {

    }

    public void render(final SpriteBatch sb)
    {

    }

    public void debug(final ShapeRenderer sr)
    {
        sr.setColor(Color.DARK_GRAY);
        sr.rect(rect.x, rect.y, rect.width, rect.height);
    }

    public Rectangle getBounds() {
        return rect;
    }
}
