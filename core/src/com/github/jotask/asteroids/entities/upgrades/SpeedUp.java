package com.github.jotask.asteroids.entities.upgrades;

/**
 * SpeedUp
 *
 * @author Jose Vives Iznardo
 * @since 31/07/2017
 */
public class SpeedUp extends Upgrade {

    public SpeedUp() {
        super(5f, 10f);
    }

    private float speedMult = 10f;
    private float forceMult = 3f;

    private float prevSpeed;
    private float prevForce;

    @Override
    public void start() {
        this.prevSpeed = this.player.maxSpeed;
        this.prevForce = this.player.maxForce;
        this.player.maxSpeed = this.prevSpeed * this.speedMult;
        this.player.maxForce = this.prevForce * this.forceMult;
    }

    @Override
    public void end() {
        this.player.maxSpeed = this.prevSpeed;
        this.player.maxForce = this.prevForce;
    }
}
