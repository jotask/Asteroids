package com.github.jotask.breakout.asteroid.upgrades;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.breakout.asteroid.Asteroids;
import com.github.jotask.breakout.asteroid.Player;
import com.github.jotask.breakout.asteroid.utils.Timer;
import com.github.jotask.breakout.asteroid.utils.Utils;

/**
 * Upgrade
 *
 * @author Jose Vives Iznardo
 * @since 31/07/2017
 */
public abstract class Upgrade {

    public enum Upgrades{

        COLOR(new ColorUp()),
        SHOOT(new ShootUp()),
        SPEED(new SpeedUp());

        public final Upgrade upgrade;
        Upgrades(final Upgrade upgrade)
        {
            this.upgrade = upgrade;
        }

    }

    private static final float[] vert = {
            -.5f, -.5f,
            .5f, -.5f,
            .5f, .5f,
            -.5f, .5f
    };

    private final Timer timer;
    protected final Player player;

    private final float LIFETIME;
    private final float UPGRADETIME;

    private Polygon polygon;

    public Upgrade(final float LIFETIME, final float UPGRADETIME) {
        this.LIFETIME = LIFETIME;
        this.UPGRADETIME = UPGRADETIME;
        this.timer = new Timer(LIFETIME);
        this.player = Asteroids.get().getPlayer();

        this.polygon = new Polygon(vert);
        this.polygon.setScale(25f, 25f);

        this.reset();

    }

    public void reset()
    {
        this.timer.setWaitTime(LIFETIME);
        final Vector2 tmp = Utils.positionInScreen(10f, 10f);
        this.polygon.setPosition(tmp.x, tmp.y);
        this.timer.reset();
    }

    public boolean isFinish() {
        return this.timer.isPassed();
    }

    public void pickUP(final Player player) {
        this.timer.setWaitTime(UPGRADETIME);
        this.timer.reset();
        player.pickUp(this);
    }

    public void update(){

    }

    public void render(final ShapeRenderer sr)
    {
        sr.polygon(this.polygon.getTransformedVertices());
    }

    public Polygon getBounds() {
        return this.polygon;
    }

    public abstract void start();
    public abstract void end();

}
