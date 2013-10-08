package com.madcowd.buildnhide.entities.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.components.render.Sprite;
import com.punchline.javalib.entities.templates.EntityTemplate;
import com.punchline.javalib.utils.Convert;

public class PlayerTemplate implements EntityTemplate{

	public PlayerTemplate() {
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		// TODO Auto-generated method stub
		
		//Body
		CircleShape ps = new CircleShape();
		ps.setRadius(Convert.pixelsToMeters(14));
		Body b = new Body(world, e, BodyType.DynamicBody, ps, new Vector2(0,20));
		e.addComponent(b);
		
		
		//Sprite TODO: turn to animation
		Sprite s = new Sprite(world.getSpriteSheet(), "playerStraight");
		s.setScale(3.5f, 3.5f);
		e.addComponent(s);
		
		
		
		return e;
	}

}