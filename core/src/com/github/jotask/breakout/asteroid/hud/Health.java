package com.github.jotask.breakout.asteroid.hud;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.github.jotask.breakout.asteroid.Asteroids;
import com.github.jotask.breakout.asteroid.entities.Player;

/**
 * Health
 *
 * @author Jose Vives Iznardo
 * @since 07/08/2017
 */
public class Health extends Component {

    private Rectangle rectangle;
    private Rectangle life;

    private float length;

    private final Player player;

    public Health(final Hud hud) {
        super(hud);
        this.rectangle = new Rectangle(0,0,100, 25f);
        this.life = new Rectangle(this.rectangle);
        this.player = Asteroids.get().getPlayer();
        this.length = this.life.getWidth();
    }

    @Override
    public void resize(OrthographicCamera camera) {

    }

    @Override
    public void update() {
        float n = this.rectangle.getWidth() * this.player.getHealth();
        this.length = n;
    }

    @Override
    public void render(ShapeRenderer sr) {
        sr.rect(rectangle.getX() + Hud.OFFSET, rectangle.getY() + Hud.OFFSET, rectangle.getWidth(), rectangle.getHeight());
        sr.set(ShapeRenderer.ShapeType.Filled);
        sr.rect(life.getX() + Hud.OFFSET, life.getY() + Hud.OFFSET, this.length, life.getHeight());
    }

}
