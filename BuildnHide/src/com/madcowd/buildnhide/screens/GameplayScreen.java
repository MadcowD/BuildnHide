package com.madcowd.buildnhide.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.madcowd.buildnhide.entities.BuildWorld;
import com.punchline.javalib.Game;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.states.InputScreen;
import com.punchline.javalib.utils.Display;

/**
 * The screen where actual gameplay takes place.
 * @author Nathaniel
 * @created Jul 24, 2013
 */
public class GameplayScreen extends InputScreen {

	private EntityWorld world;
	private OrthographicCamera camera;
	
	/**
	 * Constructs a GameplayScreen.
	 * @param game The game that owns this screen.
	 */
	public GameplayScreen(Game game) {
		super(game);
		
		camera = new OrthographicCamera(Display.getPreferredWidth(), Display.getPreferredHeight());
		
		world = new BuildWorld(game.getInput(), camera);
	}


	@Override
	public boolean keyDown(int keycode) {

		return false;
	}

	@Override
	public void render(float delta) {
		world.process();

	}

	@Override
	public void resize(int width, int height) { }

	@Override
	public void show() { 
		world.resume();
		super.show();
	}

	@Override
	public void hide() {
		world.pause();
		super.hide();
	}

	@Override
	public void resume() { }

	@Override
	public void dispose() {
		world.dispose();
	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

}
