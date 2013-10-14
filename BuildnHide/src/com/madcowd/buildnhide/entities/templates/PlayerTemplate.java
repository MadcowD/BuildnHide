package com.madcowd.buildnhide.entities.templates;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.components.render.AnimatedSprite;
import com.punchline.javalib.entities.templates.EntityTemplate;
import com.punchline.javalib.utils.Convert;

public class PlayerTemplate implements EntityTemplate {

	public PlayerTemplate() {
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {

		e.init("Player", "a", "as");
		// TODO Auto-generated method stub

		// Body
		CircleShape ps = new CircleShape();
		ps.setRadius(Convert.pixelsToMeters(14));
		Body b = new Body(world, e, BodyType.DynamicBody, ps, new Vector2(
				world.getBounds().x + 4, 5));
		b.getBody().setFixedRotation(true);
		b.getBody().setSleepingAllowed(false);
		e.addComponent(b);

		// Sprite TODO: turn to animation
		AnimatedSprite s = new AnimatedSprite(world.getSpriteSheet(), "player",
				8, Animation.LOOP, 0.1f);
		s.setState("Right", true);
		s.setScale(3.5f, 3.5f);
		s.setLayer(1);
		e.addComponent(s);

		return e;
	}

}
