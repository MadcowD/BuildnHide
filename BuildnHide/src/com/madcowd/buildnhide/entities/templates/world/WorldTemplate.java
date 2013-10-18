package com.madcowd.buildnhide.entities.templates.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.templates.EntityGroupTemplate;
import com.punchline.javalib.utils.Convert;
import com.punchline.javalib.utils.Random;

public class WorldTemplate implements EntityGroupTemplate {
	private final float TILEWIDTH_M = Convert.pixelsToMeters(8f);

	public WorldTemplate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Array<Entity> buildEntities(EntityWorld world, Object... args) {
		Array<Entity> worldEntitiesArray = new Array<Entity>();
		// ARGUMENTS
		float start = (Float) args[0];
		float end = (Float) args[1];

		// region TileBase

		// CONDITIONALS FOR RANDOM WORLD BUILDING
		Random r = new Random();
		Vector2 lastPosition = new Vector2(start, -26);
		boolean hole = false;

		for (float i = 0f; i < end - start; i += TILEWIDTH_M) {

			// set up random conditionals
			float randomValue = r.nextFloat(1000);
			Vector2 newPosition;

			if (r.nextFloat(1000) < 50) {
				hole = true;
				continue;
			}

			if (hole) {
				if (r.nextFloat(1000) > 800)
					hole = false;
				continue;

			}

			if (randomValue < 150 && lastPosition.y > -26)
				newPosition = new Vector2(i + start, lastPosition.y - 1);
			else if (randomValue > 850 && lastPosition.y < 23)
				newPosition = new Vector2(i + start, lastPosition.y + 1);
			else
				newPosition = new Vector2(i + start, lastPosition.y);

			lastPosition = newPosition;

			// ENTITY BUILDING
			Entity e = new Entity();
			e.init("Tile" + i, "World", "Object");

			PolygonShape p = new PolygonShape();
			p.setAsBox(TILEWIDTH_M / 2f, TILEWIDTH_M / 2f);
			e.addComponent(new Body(world, e, BodyType.StaticBody, p,
					newPosition));

			worldEntitiesArray.add(e);

		}

		// endregion

		return worldEntitiesArray;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}
