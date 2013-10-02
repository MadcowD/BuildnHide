package com.madcowd.buildnhide.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.templates.EntityGroupTemplate;
import com.punchline.javalib.entities.templates.EntityTemplate;

/**
 * @author MadcowD
 * Creates world chunks.
 */
public class TerrainTemplate implements EntityGroupTemplate{

	/**
	 * Creates a terrain template
	 */
	public TerrainTemplate() {
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Array<Entity> buildEntities(EntityWorld world, Object... args) {
		Array<Entity> ents = new Array<Entity>();
		
		Entity e = new Entity();
		e.init("G", "asdasd", "asd");
		PolygonShape ps = new PolygonShape();
		ps.setAsBox(20000, 20);
		e.addComponent(new Body(world, e, BodyType.StaticBody, ps, new Vector2(0,-49)));
		
		
		
		return ents;
	}
	
	


}
