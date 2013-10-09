package com.madcowd.buildnhide.entities;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.madcowd.buildnhide.entities.systems.PlayerControlSystem;
import com.madcowd.buildnhide.entities.templates.*;
import com.madcowd.buildnhide.entities.templates.scenery.BigPlanetTemplate;
import com.madcowd.buildnhide.entities.templates.scenery.BigStarTemplate;
import com.madcowd.buildnhide.entities.templates.scenery.SmallPlanetTemplate;
import com.madcowd.buildnhide.entities.templates.scenery.SmallStarTemplate;
import com.madcowd.buildnhide.entities.templates.scenery.StarFieldTemplate;
import com.madcowd.buildnhide.entities.templates.scenery.TerrainTemplate;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.systems.generic.TrackingCameraSystem;
import com.punchline.javalib.utils.Convert;
import com.punchline.javalib.utils.SoundManager;
import com.punchline.javalib.utils.SpriteSheet;

public class BuildWorld extends EntityWorld {
	
	public BuildWorld(InputMultiplexer input, Camera camera) {
		super(input, camera, new Vector2(0, -40.81f));
		
		SoundManager.playSong("shemelts", 1f, true);
		
		debugView.enabled = true;
		debugView.visible = true; //TODO: Remember to disable this...
	}

	@Override
	public void process() {
		super.process();
		
		
		if(Gdx.input.isKeyPressed(Keys.LEFT))
			camera.position.add(-2f, 0,0);
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
			camera.position.add(2f, 0,0);
		
		if(Gdx.input.isKeyPressed(Keys.UP))
			camera.position.add(0, 2f,0);
		if(Gdx.input.isKeyPressed(Keys.DOWN))
			camera.position.add(0, -2f,0);
		
		if(Gdx.input.isKeyPressed(Keys.X))
			((OrthographicCamera)camera).zoom += 0.02f;
		if(Gdx.input.isKeyPressed(Keys.Z))
			((OrthographicCamera)camera).zoom -= 0.02f;
	}

	@Override
	public Rectangle getBounds() {
		return Convert.pixelsToMeters(
				new Rectangle(
					-Gdx.graphics.getWidth() * 4, 
					-Gdx.graphics.getHeight() / 2, 
					Gdx.graphics.getWidth() * 8, 
					Gdx.graphics.getHeight()));
	}

	@Override
	protected void positionCamera() { }
	
	@Override
	protected void buildSystems() {		
		super.buildSystems();
		
		this.systems.addSystem(new PlayerControlSystem(this.input));
		this.systems.addSystem(new TrackingCameraSystem("Player", this.camera));
		
	}

	@Override
	protected void buildTemplates() {
		super.buildTemplates();
		
		this.addGroupTemplate("terrain", new TerrainTemplate());
		//Scenery
		addTemplate("BigPlanet", new BigPlanetTemplate());
		addTemplate("SmallPlanet", new SmallPlanetTemplate());
		addTemplate("BigStar", new BigStarTemplate());
		addTemplate("SmallStar", new SmallStarTemplate());
		addGroupTemplate("StarField", new StarFieldTemplate());
		
		//Entitites
		addTemplate("player", new PlayerTemplate());
		
	}
	
	@Override
	protected void buildEntities() {
		super.buildEntities();
		
		this.createEntityGroup("terrain", 256);
		this.createEntityGroup("StarField");
		this.createEntity("player");
		
	}

	@Override
	protected void buildSpriteSheet() {
		this.spriteSheet = new SpriteSheet(new Texture(Gdx.files.internal("data/Textures/SPRITESHEET.PNG"))); 
		
		//SPRITESHEET
		this.spriteSheet.addRegion("playerStraight", 28, 1, 8, 8);
		this.spriteSheet.addRegion("playerRight", 10, 1, 8, 8);
		this.spriteSheet.addRegion("playerRightRun", 19, 1, 8, 8);
		this.spriteSheet.addRegion("playerLeft", 73, 1, 8, 8);
		this.spriteSheet.addRegion("playerLeftRun", 66, 1, 8, 8);
	}

	
}
