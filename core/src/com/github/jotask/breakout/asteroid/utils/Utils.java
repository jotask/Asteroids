package com.github.jotask.breakout.asteroid.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.breakout.asteroid.Asteroids;
import com.github.jotask.breakout.asteroid.entities.upgrades.Upgrade;

/**
 * Utils
 *
 * @author Jose Vives Iznardo
 * @since 31/07/2017
 */
public final class Utils {

    public static Vector2 positionInScreen() {
        return positionInScreen(0f, 0f);
    }

    public static Vector2 positionInScreen(final float offsetX, final float offsetY) {
        final OrthographicCamera camera = Asteroids.get().getCamera();
        float x = MathUtils.random(offsetX, camera.viewportWidth - offsetX);
        float y = MathUtils.random(offsetY, camera.viewportHeight - offsetY);

        return new Vector2(x, y);
    }

    public static Vector2 getRandomVelocity()
    {
        return new Vector2(MathUtils.random(-1f, 1f), MathUtils.random(-1f, 1f));
    }

    public static Upgrade randomUpgrade()
    {
        Upgrade.Upgrades[] upgrades = Upgrade.Upgrades.values();
        final Upgrade up = upgrades[MathUtils.random(upgrades.length - 1)].upgrade;
        up.reset();
        return up;
    }

    public static Color randomColor()
    {
        final float r = MathUtils.random();
        final float g = MathUtils.random();
        final float b = MathUtils.random();
        return new Color(r, g, b, 1f);
    }

}
