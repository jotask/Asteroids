package com.github.jotask.breakout.asteroid.entities.upgrades;

/**
 * ShootUp
 *
 * @author Jose Vives Iznardo
 * @since 31/07/2017
 */
public class ShootUp extends Upgrade{

    private float prev;
    private float shoot = .1f;

    public ShootUp() {
        super(5f, 10f);
    }

    @Override
    public void start() {
        this.prev = this.player.SHOOT;
        this.player.timer.setWaitTime(this.shoot);
    }

    @Override
    public void end() {
        this.player.timer.setWaitTime(this.prev);
    }

}
