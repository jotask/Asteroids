package com.github.jotask.asteroids.hud;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.github.jotask.asteroids.Asteroids;
import com.github.jotask.asteroids.entities.Player;

/**
 * Health
 *
 * @author Jose Vives Iznardo
 * @since 07/08/2017
 */
public class Health extends Component {

    private Rectangle rectangle;
    private Rectangle health;

    private float length;

    private final Player player;

    private Life life;

    public Health(final Hud hud) {
        super(hud);
        this.rectangle = new Rectangle(0f,hud.getCamera().viewportHeight - 100f,100f, 10f);
        this.health = new Rectangle(this.rectangle);
        this.player = Asteroids.get().getPlayer();
        this.length = this.health.getWidth();

        this.life = new Life(this.hud);

    }

    @Override
    public void resize(OrthographicCamera camera) {
        life.resize(camera);
    }

    @Override
    public void update() {
        float n = this.rectangle.getWidth() * this.player.getHealth();
        this.length = n;
        life.update();
    }

    @Override
    public void render(final ShapeRenderer sr) {
        sr.rect(rectangle.getX() + Hud.OFFSET, rectangle.getY() + Hud.OFFSET * 3f, rectangle.getWidth(), rectangle.getHeight());
        sr.set(ShapeRenderer.ShapeType.Filled);
        sr.rect(health.getX() + Hud.OFFSET, health.getY() + Hud.OFFSET * 3f, this.length, health.getHeight());
        life.render(sr);
    }

    @Override
    public void renderFont(SpriteBatch sb, BitmapFont fnt) {
        fnt.draw(sb, "Health:", Hud.OFFSET, Hud.OFFSET);
        this.life.renderFont(sb, fnt);
    }

}
