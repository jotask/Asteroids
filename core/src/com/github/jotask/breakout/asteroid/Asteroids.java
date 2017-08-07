package com.github.jotask.breakout.asteroid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.github.jotask.breakout.asteroid.entities.Asteroid;
import com.github.jotask.breakout.asteroid.entities.Bullet;
import com.github.jotask.breakout.asteroid.entities.Player;
import com.github.jotask.breakout.asteroid.entities.upgrades.Upgrade;
import com.github.jotask.breakout.asteroid.hud.Hud;
import com.github.jotask.breakout.asteroid.utils.Timer;
import com.github.jotask.breakout.asteroid.utils.Utils;

import java.util.LinkedList;

/**
 * Asteroids
 *
 * @author Jose Vives Iznardo
 * @since 27/07/2017
 */
public class Asteroids extends ApplicationAdapter {

    private static Asteroids instance;

    public static Asteroids get(){ return instance; }

    private final Color c = new Color(Color.BLACK);

    private ShapeRenderer sr;

    private OrthographicCamera camera;
    private Rectangle bounds;

    private LinkedList<Asteroid> asteroids;

    private LinkedList<Bullet> bullets;

    private LinkedList<Upgrade> upgrades;

    private Player player;

    private Timer timer;

    private Hud hud;

    public Asteroids() {
        Asteroids.instance = this;
    }

    @Override
    public void create() {
        this.sr = new ShapeRenderer();
        this.sr.setAutoShapeType(true);

        this.camera = new OrthographicCamera();
        this.bounds = new Rectangle();

        this.bullets = new LinkedList<Bullet>();

        this.upgrades = new LinkedList<Upgrade>();

        this.asteroids = new LinkedList<Asteroid>();

        player = new Player();

        timer = new Timer(1f);
        this.hud = new Hud();

    }

    @Override
    public void resize(int width, int height) {
        this.camera.setToOrtho(true, width, height);
        this.camera.update();
        this.bounds.set(camera.position.x - width / 2f, camera.position.y - height / 2f, width, height);

        this.hud.resize(width, height);
    }

    private void reset()
    {
        this.asteroids.clear();
        this.bullets.clear();
        this.upgrades.clear();

        for(int i = 0; i < 1; i++)
        {
            final Asteroid a = new Asteroid();
            this.asteroids.add(a);
        }

        this.player.reset();

        this.timer.reset();

    }

    private void update() {

        player.update();

        final LinkedList<Bullet> survived = new LinkedList<Bullet>(bullets);
        for(final Bullet b: bullets) {
            b.update();

            if(b.outsideBounds(this.bounds)) {
                survived.remove(b);
                continue;
            }

            final LinkedList<Asteroid> as = new LinkedList<Asteroid>(this.asteroids);
            for(final Asteroid ast: this.asteroids)
            {
                if(b.collides(ast)) {
                    survived.remove(b);

                    LinkedList<Asteroid> childs = ast.mutate();
                    as.remove(ast);
                    as.addAll(childs);
                    // FIXME improve
                    // when a bullet collides is destroyed and the asteroid too, this can be improved
                    // much better by stop when collides
                }
            }
            this.asteroids.clear();
            this.asteroids.addAll(as);

        }

        this.bullets.clear();
        this.bullets.addAll(survived);

        for(final Asteroid a: asteroids) {
            a.update();

            if(a.collides(player))
            {
                player.damage(.1f);
            }

        }

        if (timer.isPassed(true)) {
            if(asteroids.size() < 4)
            {
                this.asteroids.add(new Asteroid());
            }
        }

        this.hud.update();

        if(this.upgrades.isEmpty() && !this.player.hasUpgrade())
            this.upgrades.add(Utils.randomUpgrade());

        final LinkedList<Upgrade> us = new LinkedList<Upgrade>(this.upgrades);
        for(final Upgrade u: this.upgrades)
        {
            u.update();

            if(u.isFinish())
            {
                us.remove(u);
            }

            if(player.collides(u))
            {
                u.pickUP(player);
                us.remove(u);
            }

        }
        this.upgrades.clear();
        this.upgrades.addAll(us);

    }

    @Override
    public void render() {

        update();

        Gdx.gl.glClearColor(c.r, c.g, c.b, c.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sr.setProjectionMatrix(this.camera.combined);
        sr.begin();
        sr.set(ShapeRenderer.ShapeType.Filled);
        for(final Asteroid a: this.asteroids)
            a.render(sr);

        for(final Upgrade u: this.upgrades)
            u.render(sr);

        for(final Bullet b: bullets)
            b.render(sr);

        player.render(sr);

        sr.end();

        sr.begin();
        this.hud.render(sr);
        sr.end();

    }

    @Override
    public void dispose() {
        sr.dispose();
        Asteroids.instance = null;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public LinkedList<Bullet> getBullets() { return bullets; }

    public Player getPlayer() { return player; }

}
