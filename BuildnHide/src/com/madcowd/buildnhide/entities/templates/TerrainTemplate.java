package com.madcowd.buildnhide.entities.templates;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.components.physical.Particle;
import com.punchline.javalib.entities.components.render.Parallax;
import com.punchline.javalib.entities.components.render.Sprite;
import com.punchline.javalib.entities.templates.EntityGroupTemplate;


/**
 * @author MadcowD
 * Creates world chunks.
 */
public class TerrainTemplate implements EntityGroupTemplate{

	/**
	 * Creates a terrain template
	 */
	public TerrainTemplate() {
		background = new Texture("data/textures/background.png");
	}
	
	Texture background;

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Array<Entity> buildEntities(EntityWorld world, Object... args) {
		Array<Entity> ents = new Array<Entity>();
		
		//region Ground
		Entity ground = new Entity();
		ground.init("G", "asdasd", "asd");
		PolygonShape ps = new PolygonShape();
		ps.setAsBox(20000, 20);
		ground.addComponent(new Body(world, ground, BodyType.StaticBody, ps, new Vector2(0,-49)));
		ents.add(ground);
		
		//endregion
		
		//region Mountains
		Entity mountains = new Entity();
		mountains.init("asd", "group", "type");
		mountains.addComponent(new Particle(mountains, new Vector2(0,0), 0f));
		
		Sprite mountainsprite = new Sprite(background,new Rectangle(0, 128, 1024, 127));
		mountainsprite.setLayer(0);
		mountains.addComponent(mountainsprite);
		
		mountains.addComponent(new Parallax(world.getCamera(), 1/64f));
		
		
		
		ents.add(mountains);
		//endregion
		
		
		//region Hills
		Entity hills = new Entity();
		hills.init("asd", "group", "type");
		hills.addComponent(new Particle(hills, new Vector2(-15,0), 0f));
		
		Sprite hillsprite = new Sprite(background,new Rectangle(0, 0, 1024, 127));
		hillsprite.setLayer(1);
		hills.addComponent(hillsprite);
		
		hills.addComponent(new Parallax(world.getCamera(), 1/32f));
		
		
		
		ents.add(hills);
		//endregion
		
		return ents;
	}
	
	


}
