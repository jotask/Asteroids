package com.github.jotask.asteroids.entities.upgrades;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.asteroids.Asteroids;
import com.github.jotask.asteroids.entities.Player;
import com.github.jotask.asteroids.hud.Component;
import com.github.jotask.asteroids.hud.UpgradeTimer;
import com.github.jotask.asteroids.entities.Entity;
import com.github.jotask.asteroids.utils.Timer;
import com.github.jotask.asteroids.utils.Utils;

/**
 * Upgrade
 *
 * @author Jose Vives Iznardo
 * @since 31/07/2017
 */
public abstract class Upgrade extends Entity {

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

    public Upgrade(final float LIFETIME, final float UPGRADETIME) {
        super(vert);
        this.LIFETIME = LIFETIME;
        this.UPGRADETIME = UPGRADETIME;
        this.timer = new Timer(LIFETIME);
        this.player = Asteroids.get().getPlayer();

        this.getPolygon().setScale(25f, 25f);

        this.reset();

    }

    public void reset()
    {
        this.timer.setWaitTime(LIFETIME);
        final Vector2 tmp = Utils.positionInScreen(10f, 10f);
        this.getPolygon().setPosition(tmp.x, tmp.y);
        this.timer.reset();
    }

    public boolean isFinish() {
        final boolean tmp = this.timer.isPassed();
        return tmp;
    }

    public void pickUP(final Player player) {
        this.timer.setWaitTime(UPGRADETIME);
        this.timer.reset();
        player.pickUp(this);

        final UpgradeTimer ut = getUpgradeTimer();
    }

    private UpgradeTimer getUpgradeTimer(){
        for(Component c: Asteroids.get().getHud().getComponents()){
            if(c instanceof UpgradeTimer)
                return (UpgradeTimer) c;
        }
        return null;
    }

    public void update(){

    }

    public void render(final ShapeRenderer sr)
    {
        sr.polygon(this.getPolygon().getTransformedVertices());
    }

    public abstract void start();
    public abstract void end();

}
