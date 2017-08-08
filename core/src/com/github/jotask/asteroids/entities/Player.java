package com.github.jotask.asteroids.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.asteroids.Asteroids;
import com.github.jotask.asteroids.entities.upgrades.Upgrade;
import com.github.jotask.asteroids.utils.Timer;

/**
 * Player
 *
 * @author Jose Vives Iznardo
 * @since 27/07/2017
 */
public class Player extends Entity{

    public static final float[] vertices = new float[] {
            -1, -1,
            2, 0f,
            -1, 1
    };

    public final float SCALE = 10f;
    public final float SHOOT = .5f;

    public float maxSpeed = 4f;
    public float maxForce = 0.1f;

    private final Vector2 pos;

    private float angle = 0f;

    public final Timer timer;

    public Color color = Color.WHITE;

    private final Vector2 acceleration = new Vector2(0, 0);
    private final Vector2 velocity = new Vector2(0, 0);
    private final Vector2 target = new Vector2();

    private Upgrade upgrade;

    private int lifes;
    private float health;

    public Player() {

        super(Player.vertices);

        this.pos = new Vector2();
        final Polygon polygon = this.getPolygon();
        polygon.setScale(SCALE, SCALE);

        this.timer = new Timer(SHOOT);

        this.target.set(Asteroids.get().getCamera().viewportWidth / 2f, Asteroids.get().getCamera().viewportHeight / 2f);

        this.health = 0f;
        this.lifes = 4;

        this.reset();

    }

    public void damage(float dmg){
        this.health -= dmg;
    }

    public void reset(){
        final OrthographicCamera cam = Asteroids.get().getCamera();
        this.pos.set(cam.position.x + (cam.viewportWidth * .5f), cam.position.y + (cam.position.y * .5f));
        this.getPolygon().setPosition(pos.x, pos.y);
        this.health = 1f;
        this.lifes--;

    }

    public void update()
    {

            if(Gdx.input.isTouched()) {
                this.target.set(Gdx.input.getX(), Gdx.input.getY());
                if (this.timer.isPassed(true))
                {
//                    Asteroids.get().getBullets().add(new Bullet(this));
                }
            }else{
                this.target.set(this.pos);
            }

            this.seek(this.target);

            this.velocity.add(acceleration);
            this.velocity.limit(maxSpeed);
            this.pos.add(velocity);
            this.acceleration.scl(0f);

            this.angle = velocity.angle();

            this.angle = (this.velocity.angle() != 0.0)? velocity.angle() : this.angle;

            this.getPolygon().setRotation(this.angle);

        final Rectangle bounds = this.getPolygon().getBoundingRectangle();
        if(bounds.getX() + bounds.getWidth() < 0f)
        {
            pos.x = Asteroids.get().getCamera().viewportWidth;
        }
        else if( bounds.getX() > Asteroids.get().getCamera().viewportWidth )
        {
            pos.x = 0f + bounds.getWidth() * .01f;
        }

        if(bounds.getY() + bounds.getHeight() < 0)
        {
            pos.y = Asteroids.get().getCamera().viewportHeight;
        }
        else if( bounds.getY() > Asteroids.get().getCamera().viewportHeight )
        {
            pos.y = 0f + bounds.getHeight() * .01f;
        }

        this.getPolygon().setPosition(pos.x, pos.y);

        if(upgrade != null)
        {
            if(this.upgrade.isFinish()){
                this.upgrade.end();
                this.upgrade = null;
            }
        }

        // FIXME
        this.health = (this.health < 0f)? 1f: this.getHealth();

    }

    public void pickUp(final Upgrade up)
    {
        this.upgrade = up;
        this.upgrade.start();
    }

    private void seek(final Vector2 t)
    {

        final Vector2 tmp = new Vector2(t.x, t.y);

        Vector2 desired = tmp.sub(pos);
        desired.nor();
        desired.scl(maxSpeed);
        // Steering = Desired minus velocity
        Vector2 steering = desired.sub(this.velocity);
        steering.limit(maxForce);

        acceleration.add(steering);

    }

    public void render(final ShapeRenderer sr)
    {
        sr.setColor(color);
        sr.polygon(this.getPolygon().getTransformedVertices());

        sr.setColor(Color.WHITE);
        sr.circle(this.target.x, this.target.y, 3f);
    }

    public float getAngle() { return angle; }

    public Vector2 getPosition() { return pos; }

    public boolean hasUpgrade()
    {
        return (this.upgrade != null);
    }

    public float getHealth() {
        return health;
    }

    public int getLifes() {
        return lifes;
    }

}
