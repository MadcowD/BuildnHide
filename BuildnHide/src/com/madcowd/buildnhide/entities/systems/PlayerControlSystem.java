package com.madcowd.buildnhide.entities.systems;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.components.physical.Transform;
import com.punchline.javalib.entities.components.render.AnimatedSprite;
import com.punchline.javalib.entities.systems.InputSystem;

public class PlayerControlSystem extends InputSystem {

	public PlayerControlSystem(InputMultiplexer input) {
		super(input);
	}

	@Override
	public boolean canProcess(Entity e) {
		return e.getTag().equals("Player");
	}

	Vector2 velocity = new Vector2(0, 0);

	Entity jayEntity;
	Vector2 jayOffset = new Vector2(0, 0);
	boolean isSmoking = false;
	boolean jump = false;

	@Override
	public void process(Entity e) {

		// MOVEMENT
		Body b = e.getComponent(Body.class);
		b.setPosition(b.getPosition().cpy()
				.add(velocity.cpy().scl(this.deltaSeconds() * 12)));

		// Jumping
		if (jump && b.getLinearVelocity().y == 0) {
			b.getBody().applyLinearImpulse(0, 30, 0, 0, true);
		}

		// ANimatyion
		AnimatedSprite as = e.getComponent(AnimatedSprite.class);
		Vector2 linv = b.getLinearVelocity();

		if (velocity.x > 0)
			as.setState("Right", true);
		else if (velocity.x < 0)
			as.setState("Left", true);

		if (linv.y == 0 && velocity.x != 0)
			as.unpause();
		else
			as.pause();

		// SMOKING CODE
		if (isSmoking) {
			if (jayEntity == null) {
				jayEntity = world.createEntity("Jay", b.getPosition().cpy()
						.add(jayOffset), as.getState());
			}

			// Update jay position
			((Transform) jayEntity.getComponent(Transform.class)).setPosition(b
					.getPosition().cpy().add(jayOffset));

			// Update jay direction
			AnimatedSprite jayAS = jayEntity.getComponent(AnimatedSprite.class);

			if (!jayAS.getState().equals(as.getState())) // IF the states
															// differ, switch
															// without a
															// continued time.
				jayAS.setState(as.getState(), false);

		} else if (jayEntity != null) {
			jayEntity.delete();
			jayEntity = null;
		}

	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.A) {
			velocity.scl(0, 1).add(-1, 0);

			// Set jayOffset
			jayOffset = new Vector2(-4f, 1.4f);
			return true;
		}

		if (keycode == Keys.D) {
			velocity.scl(0, 1).add(1, 0);

			// Set jayOffset
			jayOffset = new Vector2(2.5f, 1.125f);

			return true;
		}

		if (keycode == Keys.SPACE) {
			jump = true;
			return true;
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.A || keycode == Keys.D) {
			velocity.scl(0, 1);
			return true;
		}
		if (keycode == Keys.SPACE) {
			jump = false;
			return true;
		}

		if (keycode == Keys.J) {

			isSmoking = !isSmoking; // Toggle jay

			if (isSmoking)
				world.setTimeCoefficient(0.5f);
			else
				world.setTimeCoefficient(1f);

			return true;
		}

		return false;
	}

}
