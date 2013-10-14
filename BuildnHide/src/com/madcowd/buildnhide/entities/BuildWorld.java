package com.madcowd.buildnhide.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.madcowd.buildnhide.entities.processes.cutscene.ActivateGameProcess;
import com.madcowd.buildnhide.entities.templates.JayTemplate;
import com.madcowd.buildnhide.entities.templates.PlayerTemplate;
import com.madcowd.buildnhide.entities.templates.scenery.BigPlanetTemplate;
import com.madcowd.buildnhide.entities.templates.scenery.BigStarTemplate;
import com.madcowd.buildnhide.entities.templates.scenery.SmallPlanetTemplate;
import com.madcowd.buildnhide.entities.templates.scenery.SmallStarTemplate;
import com.madcowd.buildnhide.entities.templates.scenery.StarFieldTemplate;
import com.madcowd.buildnhide.entities.templates.scenery.TerrainTemplate;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.tiles.TileMapTemplate;
import com.punchline.javalib.utils.Convert;
import com.punchline.javalib.utils.SpriteSheet;

public class BuildWorld extends EntityWorld {

	public BuildWorld(InputMultiplexer input, Camera camera) {
		super(input, camera, new Vector2(0, -40.81f));

		// SoundManager.playSong("shemelts", 1f, true);

		debugView.enabled = true;
		debugView.visible = true; // TODO: Remember to disable this...
	}

	@Override
	public void process() {
		super.process();
	}

	@Override
	public Rectangle getBounds() {
		return Convert.pixelsToMeters(new Rectangle(
				-Gdx.graphics.getWidth() * 5, -Gdx.graphics.getHeight() / 2,
				Gdx.graphics.getWidth() * 10, Gdx.graphics.getHeight()));
	}

	@Override
	protected void positionCamera() {
		camera.position.set(-3600, 0, 0);
	}

	@Override
	protected void buildSystems() {
		super.buildSystems();

	}

	@Override
	protected void buildTemplates() {
		super.buildTemplates();

		this.addGroupTemplate("terrain", new TerrainTemplate());
		// Scenery
		addTemplate("BigPlanet", new BigPlanetTemplate());
		addTemplate("SmallPlanet", new SmallPlanetTemplate());
		addTemplate("BigStar", new BigStarTemplate());
		addTemplate("SmallStar", new SmallStarTemplate());
		addGroupTemplate("StarField", new StarFieldTemplate());

		// Misc-Props
		addTemplate("Jay", new JayTemplate());

		// Entitites
		addTemplate("player", new PlayerTemplate());

		addTemplate("TileMap", new TileMapTemplate());

	}

	@Override
	protected void buildEntities() {
		super.buildEntities();

		this.createEntityGroup("terrain", 256);
		this.createEntityGroup("StarField");
		this.createEntity("player");

		// TEST OF CONCEPT.
		this.processes.attach(new ActivateGameProcess());

		this.createEntity("TileMap", "data/game.tmx", "data/Textures/tiles.png");

	}

	@Override
	protected void buildSpriteSheet() {
		this.spriteSheet = new SpriteSheet(new Texture(
				Gdx.files.internal("data/Textures/SPRITESHEET.PNG")));

		// SPRITESHEET
		this.spriteSheet.addRegion("playerRight", 10, 1, 17, 8);
		this.spriteSheet.addRegion("playerLeft", 66, 1, 17, 8);
		this.spriteSheet.addRegion("JayRight", 0, 38, 126, 10);
		this.spriteSheet.addRegion("JayLeft", 0, 48, 126, 10);
	}

}
