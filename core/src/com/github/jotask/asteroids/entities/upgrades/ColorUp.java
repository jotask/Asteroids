package com.github.jotask.asteroids.entities.upgrades;

import com.badlogic.gdx.graphics.Color;
import com.github.jotask.asteroids.utils.Utils;

/**
 * ColorU
 *
 * @author Jose Vives Iznardo
 * @since 31/07/2017
 */
public class ColorUp extends Upgrade{

    private Color color;
    private Color prev;

    public ColorUp() {
        super(5f, 3f);
        this.prev = player.color;
        this.color = Color.RED;
    }

    @Override
    public void reset() {
        super.reset();
        this.color = Utils.randomColor();
    }

    @Override
    public void start() {
        this.player.color = color;
    }

    @Override
    public void end() {
        this.player.color = prev;
    }
}
