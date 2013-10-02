package com.madcowd.buildnhide.entities;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.utils.Convert;

public class BuildWorld extends EntityWorld {
	
	public BuildWorld(InputMultiplexer input, Camera camera) {
		super(input, camera, new Vector2(0, 0));
		
		debugView.enabled = true;
		debugView.visible = true; //TODO: Remember to disable this...
	}

	@Override
	public void process() {
		super.process();
	}

	@Override
	public Rectangle getBounds() {
		return Convert.pixelsToMeters(
				new Rectangle(
					-Gdx.graphics.getWidth() * 2, 
					-Gdx.graphics.getHeight() / 2, 
					Gdx.graphics.getWidth() * 4, 
					Gdx.graphics.getHeight()));
	}

	@Override
	protected void positionCamera() { }
	
	@Override
	protected void buildSystems() {		
		super.buildSystems();
		
	}

	@Override
	protected void buildTemplates() {
		super.buildTemplates();
		
	}
	
	@Override
	protected void buildEntities() {
		super.buildEntities();
		
		
	}

	@Override
	protected void buildSpriteSheet() {
		
	}

	
}
