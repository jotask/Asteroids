package com.github.jotask.breakout.breakout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.LinkedList;

public class BreakOut extends ApplicationAdapter {

	ShapeRenderer sr;
	SpriteBatch sb;

	OrthographicCamera camera;

	private Ball ball;

	private LinkedList<Break> breaks;

	private Player player;

	@Override
	public void create () {

		sb = new SpriteBatch();
		sr = new ShapeRenderer();
		sr.setAutoShapeType(true);

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, +10f);
		camera.update();

		ball = new Ball();

		this.breaks = new LinkedList<Break>();
		for(int i = 0; i < 1; i++)
		{
			this.breaks.add(new Break());
		}

		this.player = new Player();
	}

	@Override
	public void render () {

		ball.update();
//		for(Break b: breaks){
//			if(ball.collide(b.getBounds()))
//				break;
//		}

		player.update();
		ball.collide(player.rect);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		ball.render(sb);
		for(Break b: breaks) b.render(sb);
		player.render(sb);
		sb.end();

		sr.setProjectionMatrix(camera.combined);
		sr.begin();
		sr.set(ShapeRenderer.ShapeType.Filled);
		for(Break b: breaks) b.debug(sr);
		player.debug(sr);
		ball.debug(sr);
		sr.end();

	}
	
	@Override
	public void dispose () {
		sb.dispose();
		sr.dispose();
	}
}
