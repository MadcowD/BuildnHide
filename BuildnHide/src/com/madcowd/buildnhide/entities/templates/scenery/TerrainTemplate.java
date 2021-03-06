package com.madcowd.buildnhide.entities.templates.scenery;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.components.physical.Particle;
import com.punchline.javalib.entities.components.render.Parallax;
import com.punchline.javalib.entities.components.render.Sprite;
import com.punchline.javalib.entities.templates.EntityGroupTemplate;
import com.punchline.javalib.utils.Convert;

/**
 * @author MadcowD Creates world chunks.
 */
public class TerrainTemplate implements EntityGroupTemplate {

	/**
	 * Creates a terrain template
	 */
	public TerrainTemplate() {
		background = new Texture("data/textures/background.png");
		tiles = new Texture("data/textures/Scenery/tiles.png");
	}

	Texture background;
	Texture tiles;

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Array<Entity> buildEntities(EntityWorld world, Object... args) {
		Array<Entity> ents = new Array<Entity>();

		// region Ground
		Entity ground = new Entity();
		ground.init("G", "asdasd", "asd");
		PolygonShape ps = new PolygonShape();
		ps.setAsBox(Convert.pixelsToMeters(123 / 2),
				Convert.pixelsToMeters(64 / 2));
		Body groundBody = (Body) ground.addComponent(new Body(world, ground,
				BodyType.StaticBody, ps, new Vector2(world.getBounds().x + 6,
						-28)));

		ground.addComponent(groundBody);
		Sprite s = new Sprite(tiles, new Rectangle(0, 0, 128, 127),
				new Vector2(128 / 2f + -6, 32f));

		ground.addComponent(s);
		ground.addComponent(com.punchline.javalib.entities.GenericCollisionEvents
				.empty());
		ents.add(ground);

		// endregion

		// region Mountains
		Entity mountains = new Entity();
		mountains.init("asd", "group", "type");
		mountains.addComponent(new Particle(mountains, new Vector2(25, -16f),
				0f));

		Sprite mountainsprite = new Sprite(background, new Rectangle(0, 201,
				2048, 242));
		mountainsprite.setLayer(-100);
		mountains.addComponent(mountainsprite);

		mountains.addComponent(new Parallax(world.getCamera(), 1 / 16f));

		ents.add(mountains);
		// endregion

		// region Hills
		Entity hills = new Entity();
		hills.init("asd", "group", "type");
		hills.addComponent(new Particle(hills, new Vector2(15, -23), 0f));

		Sprite hillsprite = new Sprite(background, new Rectangle(0, 0, 2048,
				200));
		hillsprite.setLayer(-99);
		hills.addComponent(hillsprite);

		hills.addComponent(new Parallax(world.getCamera(), 1 / 8f));

		ents.add(hills);
		// endregion

		return ents;
	}
}
