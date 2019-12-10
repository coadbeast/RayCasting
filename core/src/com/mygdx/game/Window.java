package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Window extends ApplicationAdapter {
	public static ShapeRenderer shapeRenderer;
	private Boundary[] walls;
	Maze maze;
	private Particle particle;
	private boolean view;
	public static float angle = 0;

	final float sceneW = 800f;
	final float sceneH = 800f;
	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		particle = new Particle();
		view = false;
		Random random = new Random();
		maze = new Maze();
		for(int i = 0; i < 100000; i++){
			maze.generate();
		}
		walls = maze.getHorisontalAndVertical();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.setColor(255,255,255,100);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		for (Boundary wall: walls) {
			wall.render();
		}
		particle.render(walls);
		shapeRenderer.end();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		particle.look(walls);
		shapeRenderer.end();
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			if (particle.collide())
			particle.update();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			angle -= 5;
			particle.setAngle(angle);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			angle += 5;
			particle.setAngle(angle);
		}

	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}
