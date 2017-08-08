package com.github.jotask.asteroids.hud;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.asteroids.Asteroids;
import com.github.jotask.asteroids.entities.Player;

/**
 * Life
 *
 * @author Jose Vives Iznardo
 * @since 07/08/2017
 */
public class Life extends Component {

    private final Player player;

    private final Vector2 start;

    private final Polygon polygon;

    public Life(Hud hud) {
        super(hud);
        this.player = Asteroids.get().getPlayer();
        this.polygon = new Polygon(Player.vertices);
        this.polygon.setScale(5f,5f);
        this.polygon.rotate(-45f);
        this.start = new Vector2(0f, 45f);
        this.start.x = hud.getCamera().viewportWidth - (this.polygon.getBoundingRectangle().getWidth() * .5f) - Hud.OFFSET;
        this.start.x -= 100f;
//        this.start.x = hud.getCamera().viewportWidth + 50f;
    }

    @Override
    public void update() {

    }

    @Override
    public void resize(OrthographicCamera camera) {

    }

    @Override
    public void render(final ShapeRenderer sr) {
        final Rectangle off = this.polygon.getBoundingRectangle();
        for(int i = 0; i < player.getLifes(); i++) {
            sr.polygon(this.polygon.getTransformedVertices());
            this.polygon.setPosition(this.polygon.getX() + off.getWidth() + 5f, this.polygon.getY());
        }
        this.polygon.setPosition(start.x, start.y);
    }

    @Override
    public void renderFont(SpriteBatch sb, BitmapFont fnt) {
        fnt.draw(sb, "Lifes:", this.start.x, Hud.OFFSET);
    }

}
