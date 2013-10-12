package com.madcowd.buildnhide.entities.templates;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.physical.Particle;
import com.punchline.javalib.entities.components.render.AnimatedSprite;
import com.punchline.javalib.entities.templates.EntityTemplate;

public class JayTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		// Init
		e.init("PlayerJay", "Misc", "Prop");

		// Args
		Vector2 position = (Vector2) args[0];
		String direction = (String) args[1];

		// Sprite
		int loopType = Animation.LOOP;

		AnimatedSprite as = new AnimatedSprite(world.getSpriteSheet(), "Jay"
				+ direction, 16, 1, 0, loopType, 0.1f);
		as.setScale(2.5f, 2.5f);
		as.setLayer(2);
		e.addComponent(as);

		// Particle
		Particle p = new Particle(e, position, 0f);
		e.addComponent(p);

		return e;
	}
}
